# Etapa 1: build da aplicação Angular
FROM node:20-alpine AS build

WORKDIR /app

# Copiar package.json e instalar dependências
COPY package*.json ./
RUN npm install -g @angular/cli && npm install

# Copiar código fonte
COPY . .

# Gerar build de produção
RUN ng build --configuration production

# Etapa 2: usar Nginx para servir
FROM nginx:1.27-alpine

# Remover config default
RUN rm -rf /usr/share/nginx/html/*

# Copiar arquivos buildados para a pasta pública do nginx
COPY --from=build /app/dist/user-interface/browser /usr/share/nginx/html

# Configuração customizada para SPA (rotas Angular funcionarem direto no browser)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
