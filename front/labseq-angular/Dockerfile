FROM node:20-bullseye AS build

WORKDIR /usr/src/app

COPY package*.json ./
RUN npm install -g @angular/cli
RUN npm i

COPY . .

RUN ng build --configuration production

FROM nginx:alpine

RUN rm -rf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build /usr/src/app/dist/labseq-angular/browser/ /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
