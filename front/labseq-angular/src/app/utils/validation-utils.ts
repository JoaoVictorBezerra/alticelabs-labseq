export class ValidationUtils {
  static isNan(n: any, errorMessage: string): boolean {
    return isNaN(n) || n === 'NaN';

  }

  static isInfinity(n: any, errorMessage: string): boolean {
    return n === 'Infinity' || n === Infinity || n === 'infinity';
  }
}
