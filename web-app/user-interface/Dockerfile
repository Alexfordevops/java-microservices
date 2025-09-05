# Etapa 1: build Angular
FROM node:20-alpine AS build
WORKDIR /app

# Copiar package.json e instalar dependências
COPY package*.json ./
RUN npm install -g @angular/cli && npm install

# Copiar código fonte e gerar build
COPY . .
RUN ng build --configuration production

# Etapa 2: Nginx
FROM nginx:1.27-alpine

# Limpar conteúdo padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copiar build Angular
COPY --from=build /app/dist/user-interface/browser /usr/share/nginx/html

# Copiar conf Nginx global
COPY nginx.conf /etc/nginx/nginx.conf

# Testar sintaxe antes de rodar
RUN nginx -t

EXPOSE 80

# Comando padrão
CMD ["nginx", "-g", "daemon off;"]
