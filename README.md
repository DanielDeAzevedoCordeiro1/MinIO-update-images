# Minio Update Images

Este é um projeto Spring Boot que permite o upload e recuperação de imagens utilizando o MinIO como armazenamento de objetos. O sistema armazena metadados das imagens em um banco de dados relacional e fornece endpoints REST para interação com as imagens.

## Funcionalidades

- **Upload de Imagens**: Envie arquivos de imagem (formato PNG) via endpoint POST.
- **Recuperação de Imagens**: Recupere imagens armazenadas utilizando um ID único via endpoint GET.
- **Armazenamento**: Usa MinIO como sistema de armazenamento de objetos e um banco de dados para metadados.

## Tecnologias Utilizadas

- **Java**: Linguagem principal.
- **Spring Boot**: Framework para construção da aplicação.
- **MinIO**: Servidor de armazenamento de objetos.
- **Spring JDBC**: Para interação com o banco de dados.
- **Apache Commons IO**: Utilitário para manipulação de streams.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 17 ou superior
- Maven
- MinIO (pode ser executado localmente ou em um servidor remoto)
- Um banco de dados relacional (ex.: PostgreSQL, MySQL) com a tabela `images` criada

### Estrutura da Tabela no Banco de Dados

Crie a tabela `images` com o seguinte SQL:

```sql
CREATE TABLE images (
    id SERIAL PRIMARY KEY,
    object_id VARCHAR(36) NOT NULL UNIQUE
);


src/main/java/com/example/minioupdateimages/
├── client
│   └── MinioClientConfig.java       # Configuração do cliente MinIO
├── controller
│   └── UpdateImageController.java   # Endpoints REST para upload e recuperação
└── service
    └── UploadImageService.java      # Lógica de upload e armazenamento
