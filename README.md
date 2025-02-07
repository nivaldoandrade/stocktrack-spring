## Demonstração

Para fins de demonstração, foi realizado o deploy da API em uma instância na oracle cloud. Foi utilizado o docker compose, nginx para proxy reverso e Let's Encrypt para a configuração do certificado digital e habilitação de HTTPS. No link abaixo, é possível utilizar o Swagger para testar a API:

[Acessar Swagger UI - API Demo](https://stocktrackapi.nivaldoandrade.dev.br/swagger-ui/index.html)

#### **Credenciaia de Teste**
Para realizar testes, utilize as credenciais abaixo:
- Usuário Admin:
  - Usuário: admin
  - Senha: 12345678
---

## Exemplo de uso da API

Abaixo está um fluxo básico de uso da API que demonstra sua principal funcionalidade:

1. **Autenticação:**  
  Relize o login para obter um token JWT, que será usado para autenticar todas as requisições protegidas.
2. **Criação de categoria e warehouse:**  
  Antes de criar um produto é necessário criar uma categoria e warehouse, pois o produto precisa estar associado a essas entidades.
3. **Criação de produto:**  
  Com a categoria e warehouse criados, é possível criar um novo produto, incluindo informações como code, brand, estoque disponível em um ou mais warehouse

Todos os detalhes de cada endpoint, incluindo corpo da requisição e resposta estão disponíveis na documentação do Swagger.

---

## **Documentação da API**

Para visualizar a documentação interativa da API, você pode usar o Swagger. O Swagger fornece uma interface gráfica onde você pode explorar e testar as endpoints da API.

Após iniciar os serviços, a documentação do Swagger estará disponível em:
```bash
  #Local
  http://localhost:8080/swagger-ui/index.html
  
  #Online
  https://stocktrackapi.nivaldoandrade.dev.br/swagger-ui/index.html
  
  usuário: admin
  password: 12345678
```
Navegue até este URL no seu navegador para acessar a interface do Swagger, onde você poderá visualizar e interagir com a documentação da API.

---

## Rodando em modo desenvolvimento com banco de dados dockerizado

### 1. Clone o Repositório

Clone o repositório para o seu ambiente local:
```bash
  git clone https://github.com/nivaldoandrade/stocktrack-spring
```

### 2. Configurar as Variáveis de Ambiente 

Edite as variáveis de ambiente no arquivo localizado em src/main/resources/application-dev.properties. Para mais informações sobre as variáveis de ambiente de desenvolvimento, consulte a seção: [Configuração do Arquivo application-dev.properties](#configuração-do-arquivo-application-devproperties)

#### **Observações Importantes:**
1. **Banco de Dados Dockerizado:** Não é necessário preencher as variáveis `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password`, pois as configurações serão automaticamente gerenciadas pelo arquivo `docker-compose-dev.yml` na raiz do projeto;
2. **Ativação do Docker Compose:** Para utilizar o banco de dados com o Docker, defina a variável `spring.docker.compose.file` apontando para o caminho do arquivo `docker-compose-dev.yml`;
3. **Sincronização de Variáveis:** Se alterar o valor da variável `POSTGRES_DB` no arquivo `docker-compose-dev.yml`, lembre-se de atualizar a variável `spring.flyway.placeholders.db_name` no arquivo `application-dev.properties` com o mesmo valor.

### 3. Iniciando a aplicação 

Você pode iniciar a aplicação de duas formas:

1.	Executando a classe principal `StockTrackApplication` (src/main/java/StockTrackApplication.java) diretamente na sua IDE preferida.
2.	Através do terminal, seguindo os passos abaixo:

Passo a passo:

Entre no diretório do projeto:

```bash
  cd stocktrack-api
```

Inicie a aplicação utilizando o profile dev com o seguinte comando:

```bash
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
---

## Rodando em modo desenvolvimento com banco de dados não dockerizado

### 1. Clone o Repositório

Clone o repositório para o seu ambiente local:
```bash
  git clone https://github.com/nivaldoandrade/stocktrack-spring
```

### 2. Configurar as Variáveis de Ambiente

Edite as variáveis de ambiente no arquivo localizado em src/main/resources/application-dev.properties. Para mais informações sobre as variáveis de ambiente de desenvolvimento, consulte a seção: [Configuração do Arquivo application-dev.properties](#configuração-do-arquivo-application-devproperties)

#### **Observações Importantes:**
1. **Banco de Dados Não Dockerizado:** É necessário ter PostgreSQL instalado e executando. Crie um banco de dados manualmente e preencha as variáveis `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password`;
2. **Desativação do Docker Compose:** Para utilizar o banco de dados sem Docker, defina a variável `spring.docker.compose.enabled` como `false`;
3. **Sincronização de Variáveis:** Lembre-se de atualizar a variável `spring.flyway.placeholders.db_name` no arquivo `application-dev.properties` com o mesmo nome do banco da dados especificado na variável `spring.datasource.url`.

### 3. Iniciando a aplicação

Você pode iniciar a aplicação de duas formas:

1.	Executando a classe principal `StockTrackApplication` (src/main/java/StockTrackApplication.java) diretamente na sua IDE preferida.
2.	Através do terminal, seguindo os passos abaixo:

Passo a passo:

Entre no diretório do projeto:

```bash
  cd stocktrack-api
```

Inicie a aplicação utilizando o profile dev com o seguinte comando:

```bash
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
---

## Rodando em modo produção com docker compose

### 1. Clone o Repositório

Clone o repositório para o seu ambiente local:
```bash
  git clone https://github.com/nivaldoandrade/stocktrack-spring
```

### 2. Configurar as Variáveis de Ambiente

Renomei o arquivo `.env.example` para `.env` dentro da pasta `docker` e edite as variáveis de ambiente. Para mais informações sobre as variáveis de ambiente de desenvolvimento, consulte a seção: [Configuração do Arquivo application-prod.properties](#configuração-do-arquivo-application-prodproperties)

#### **Observações Importantes:**
1. **Sincronização de Variáveis:** 
   - Lembre-se de atualizar a variável `DB_PORT` no arquivo `.env` com o mesmo número da porta do banco da dados configurada no arquivo docker compose `docker-compose-full.yml`. Por padrão, o docker compose já utiliza a porta padrão do PostgreSQL. 
   - O valor da variável `DB_LOCALHOST` no arquivo `.env` tem que ser o mesmo nome do serviço do banco de dados configurado no `docker-compose-full`. Esse nome é usado para resolver o host do banco de dados dentro do container da aplicação.

### 3. Iniciando a aplicação

Entre no diretório do projeto:

```bash
  cd stocktrack-api
```

Inicie a aplicação utilizando o seguinte comando:

```bash
  docker compose -f docker/docker-compose-full.yml up
```

---

## Variáveis de Ambiente

### Configuração do Arquivo `application-dev.properties`

Este arquivo contém as propriedades utilizadas no ambiente de desenvolvimento do projeto. Abaixo estão as descrições detalhadas de cada configuração:

#### Banco de Dados

- **`spring.datasource.url`**  
  URL de conexão com o banco de dados PostgreSQL.  
  **Formato:** `jdbc:postgresql://localhost:5432/stocktracktest`
  - Valor padrão:  
    - host: localhost
    - porta: 5432
    - nome do banco: stocktracktest

- **`spring.datasource.username`**  
  Nome do usuário do banco de dados.
    - Padrão: `root`

- **`spring.datasource.password`**  
  Senha do banco de dados.
    - Padrão: `root`

- **`spring.flyway.placeholders.db_name`**  
  Placeholder utilizado pelo Flyway para o nome do banco de dados.
    - Padrão: `stocktracktest`

- **`spring.flyway.placeholders.db_timezone`**  
  Placeholder utilizado pelo Flyway para o timezone.
    - Padrão: `America/Sao_Paulo`

---

#### Spring Docker

- **`spring.docker.compose.enabled`**  
  Ativa ou desativa a utilização do docker compose. (Opcional).

- **`spring.docker.compose.file`**  
  Define o nome do arquivo docker compose. Ao definir essa variável, não é necessário ativar explicitamente a variável `spring.docker.compose.enabled`.
  
---

#### Segurança e JWT

- **`security.jwt.secret-key`**  
  Chave secreta usada para assinar tokens JWT.
    - Padrão: `93624b6ac3de8f0d54043c9d4c6827d2b921127dd58f2699610cf064e8659113`

- **`security.jwt.expires-in`**  
  Tempo de expiração do token JWT em milissegundos.
    - Padrão: `86400000` (1 dia)

---

#### AWS

- **`aws.enabled`**  
  Ativa ou desativa a utilização de serviços da AWS.
    - Padrão: `false`

- **`aws.credentials.accessKey`**  
  Access Key da AWS usada para autenticação. Deve ser configurada apenas se aws.enabled=true.

- **`aws.credentials.secretKey`**  
  Secret Key da AWS usada para autenticação. Deve ser configurada apenas se aws.enabled=true.

---

#### S3

- **`storage.s3.region`**  
  Região configurada para o bucket no S3.

- **`storage.s3.bucket-name`**  
  Nome do bucket usado para armazenar arquivos no S3.

**Atenção:** Ao utilizar S3 é necessário configurar as propriedades da [AWS](#aws).

---

#### Armazenamento Local

- **`storage.local.upload-dir`**  
  Diretório local onde os arquivos serão armazenados no ambiente de desenvolvimento.
    - Variável de ambiente: `DEV_STORAGE_FOLDER_NAME` - Padrão: `uploads`.

---

#### Tipo de Armazenamento

- **`storage.type`**  
  Define o tipo de armazenamento a ser utilizado.
    - Padrão: `local` - Opções válidas:
        - local: Para armazenamento local no servidor.
        - s3: Para armazenamento na nuvem usando Amazon S3.
      
**Atenção:** Qualquer valor diferente de `local` ou `s3` pode resultar em erro ou comportamento inesperado. 
Ao utilizar `s3` é necessário configurar as propriedades do [S3](#s3).

---

### Configuração do Arquivo `application-prod.properties`

Este arquivo contém as propriedades utilizadas no ambiente de produção do projeto. Abaixo estão as descrições detalhadas de cada configuração:

---

#### Banco de Dados

- **`spring.datasource.url`**  
  URL de conexão com o banco de dados PostgreSQL.  
  **Formato:** `jdbc:postgresql://${DB_HOSTNAME}:${DB_PORT}/${DB_NAME}`
    - Utiliza variáveis de ambiente:
        - `DB_HOSTNAME`: Host do banco de dados.
        - `DB_PORT`: Porta do banco de dados.
        - `DB_NAME`: Nome do banco de dados.

- **`spring.datasource.username`**  
  Nome do usuário do banco de dados.
    - Variável de ambiente: `USERNAME_DB`.

- **`spring.datasource.password`**  
  Senha do banco de dados.
    - Variável de ambiente: `PASSWORD_DB`.

---

#### Flyway (Migração de Banco de Dados)

- **`spring.flyway.placeholders.db_name`**  
  Placeholder utilizado pelo Flyway para o nome do banco de dados.
    - Variável de ambiente: `DB_NAME`.

- **`spring.flyway.placeholders.db_timezone`**  
  Placeholder utilizado pelo Flyway para o timezone.
    - Variável de ambiente: `DB_TIMEZONE`. Padrão: `America/Sao_Paulo`.

---

#### Segurança e JWT

- **`security.jwt.secret-key`**  
  Chave secreta usada para assinar tokens JWT.
    - Variável de ambiente: `JWT_SECRET_KEY`.

- **`security.jwt.expires-in`**  
  Tempo de expiração do token JWT em milissegundos.
    - Variável de ambiente: `JWT_EXPIRES_IN`.

---

#### AWS - Prod

- **`aws.enabled`**  
  Ativa ou desativa a utilização de serviços da AWS.
    - Variável de ambiente: `AWS_ENABLED` - Opções válidas:
        - true: Para ativar AWS.
        - false: Para desativar AWS.

- **`aws.credentials.accessKey`**  
  Access Key da AWS usada para autenticação. Deve ser configurada apenas se aws.enabled=true.
    - Variável de ambiente: `AWS_CREDENTIALS_ACCESSKEY`.

- **`aws.credentials.secretKey`**  
  Secret Key da AWS usada para autenticação. Deve ser configurada apenas se aws.enabled=true.
    - Variável de ambiente: `AWS_CREDENTIALS_SECRETKEY`.

---

#### S3 - Prod

- **`storage.s3.region`**  
  Região configurada para o bucket no S3.
    - Variável de ambiente: `AWS_S3_REGION`.

- **`storage.s3.bucket-name`**  
  Nome do bucket usado para armazenar arquivos no S3.
    - Variável de ambiente: `AWS_S3_BUCKETNAME`.

**Atenção:** Ao utilizar S3 é necessário configurar as propriedades da [AWS](#aws---prod).

---

#### Armazenamento Local

- **`storage.local.upload-dir`**  
  Diretório local onde os arquivos serão armazenados no ambiente de produção.
    - Variável de ambiente: `STORAGE_FOLDER_NAME` - Padrão: `uploads`.

---

#### Tipo de Armazenamento

- **`storage.type`**  
  Define o tipo de armazenamento a ser utilizado.
    - Variável de ambiente: `STORAGE_TYPE` - Opções válidas:
        - local: Para armazenamento local no servidor.
        - s3: Para armazenamento na nuvem usando Amazon S3.

**Atenção:** Qualquer valor diferente de `local` ou `s3` pode resultar em erro ou comportamento inesperado.
Ao utilizar `s3` é necessário configurar as propriedades do [S3](#s3---prod).

---
