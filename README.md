## Variáveis de Ambiente

### Configuração do Arquivo `application-dev.properties`

Este arquivo contém as propriedades utilizadas no ambiente de desenvolvimento do projeto. Abaixo estão as descrições detalhadas de cada configuração:

#### Banco de Dados

- **`spring.datasource.url`**  
  URL de conexão com o banco de dados PostgreSQL.  
  **Formato:** `jdbc:postgresql://${DEV_DB_HOSTNAME:localhost}:${DEV_DB_PORT:5432}/${DEV_DB_NAME:stocktrack-test}`
    - Utiliza variáveis de ambiente:
        - `DEV_DB_HOSTNAME`: Host do banco de dados - Padrão: `localhost`.
        - `DEV_DB_PORT`: Porta do banco de dados - Padrão: `5432`.
        - `DEV_DB_NAME`: Nome do banco de dados - Padrão: `stocktrack-test`.

- **`spring.datasource.username`**  
  Nome do usuário do banco de dados.
    - Variável de ambiente: `DEV_USERNAME_DB` - Padrão: `root`.

- **`spring.datasource.password`**  
  Senha do banco de dados.
    - Variável de ambiente: `DEV_PASSWORD_DB` - Padrão: `root`.

- **`spring.flyway.placeholders.db_name`**  
  Placeholder utilizado pelo Flyway para o nome do banco de dados.
    - Variável de ambiente: `DEV_DB_NAME` - Padrão: `stocktrack-test`.

- **`spring.flyway.placeholders.db_timezone`**  
  Placeholder utilizado pelo Flyway para o timezone.
    - Variável de ambiente: `DEV_DB_TIMEZONE` - Padrão: `America/Sao_Paulo`.

---

#### Segurança e JWT

- **`security.jwt.secret-key`**  
  Chave secreta usada para assinar tokens JWT.
    - Variável de ambiente: `DEV_JWT_SECRET_KEY` - Padrão: `93624b6ac3de8f0d54043c9d4c6827d2b921127dd58f2699610cf064e8659113`.

- **`security.jwt.expires-in`**  
  Tempo de expiração do token JWT em milissegundos.
    - Variável de ambiente: `DEV_JWT_EXPIRES_IN` - Padrão: `86400000` (1 dia).

---

#### AWS

- **`aws.enabled`**  
  Ativa ou desativa a utilização de serviços da AWS.
    - Variável de ambiente: `DEV_AWS_ENABLED` - Padrão: `false`.

- **`aws.credentials.accessKey`**  
  Access Key da AWS usada para autenticação. Deve ser configurada apenas se aws.enabled=true.
    - Variável de ambiente: `DEV_AWS_CREDENTIALS_ACCESSKEY`.

- **`aws.credentials.secretKey`**  
  Secret Key da AWS usada para autenticação. Deve ser configurada apenas se aws.enabled=true.
    - Variável de ambiente: `DEV_AWS_CREDENTIALS_SECRETKEY`.

---

#### S3

- **`storage.s3.region`**  
  Região configurada para o bucket no S3. 
    - Variável de ambiente: `DEV_AWS_S3_REGION`.

- **`storage.s3.bucket-name`**  
  Nome do bucket usado para armazenar arquivos no S3.
    - Variável de ambiente: `DEV_AWS_S3_BUCKETNAME`.

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
    - Variável de ambiente: `DEV_STORAGE_TYPE` - Padrão: `local` - Opções válidas:
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
