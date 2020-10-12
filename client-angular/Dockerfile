FROM node:10 as build-step
WORKDIR /app
COPY package.json ./
RUN npm install @angular/cli -g
RUN npm i
RUN ng build --prod


FROM nginx:1.17.1-alpine
RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /usr/src/sample/dist/myapp /usr/share/nginx/html/
CMD ["nginx", "-g", "daemon off;"]