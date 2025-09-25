import {Component, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {LabSeqService} from './service/labseq';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ValidationUtils} from './utils/validation-utils';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('LabSeq Calculator');
  protected labSeqService: LabSeqService;
  protected term: BigInt | undefined = undefined;
  protected isLoading: boolean = false;
  protected errorMessage: string = '';
  protected readonly parseInt = parseInt;

  public inputValue: string = '';

  constructor(private _labSeqService: LabSeqService) {
    this.labSeqService = _labSeqService;
  }

  public getLabSeq(term: number) {
    this.term = undefined;
    this.errorMessage = '';

    if (!term && term !== 0) {
      this.errorMessage = 'Please enter a valid number';
      return;
    }

    if (term < 0) {
      this.errorMessage = 'Please enter a non-negative number';
      return;
    }

    if (term > 1000) {
      this.errorMessage = 'Please enter a number less than or equal to 1000 for better performance';
      return;
    }

    this.isLoading = true;

    this.labSeqService.getLabSeq(term)
      .subscribe({
        next: (response) => {
          this.isLoading = false;
          if (response && response.data !== undefined) {
            const data = response.data;

            if (ValidationUtils.isInfinity(data, this.errorMessage)) {
              this.errorMessage = 'Invalid result returned. Please try again.';
              return;
            }
            if (ValidationUtils.isNan(data, this.errorMessage)) {
              this.errorMessage = 'The result is too large to display (Infinity). Try a smaller number.';
              return;
            }

            try {
              this.term = BigInt(data);
              console.log(`LabSeq(${term}) = ${this.term}`);
            } catch (error) {
              this.errorMessage = 'Result is too large to display. Try a smaller number.';
              console.error('BigInt conversion error:', error);
            }
          } else {
            this.errorMessage = 'No data received from the server.';
          }
        },
        error: (error) => {
          this.isLoading = false;
          console.error('API Error:', error);

          if (error.status === 0) {
            this.errorMessage = 'Unable to connect to the server. Please check if the backend is running.';
          } else if (error.status === 500) {
            this.errorMessage = 'Server error occurred. The calculation might be too complex.';
          } else if (error.status === 400) {
            this.errorMessage = 'Invalid input. Please check your number.';
          } else {
            this.errorMessage = '.';
          }
        }
      });
  }

}
