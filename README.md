# Literalura

## Descrição
Projeto desenvolvido no programa ONE - Oracle Next Education realizado pela Alura em parceria com a Oracle.
O projeto foi desenvolvido em Java 17 no decorrer da especialização em back-end. 
O ForumHub é uma API que permite aos usuários interagirem entre si por meio da criação, atualização, exclusão e visualização de tópicos e respostas em um fórum de discussão.
Sua construção segue uma arquitetura RESTful e foi realizado utilizando Spring Boot.

## Funcionalidades
A aplicação oferece as seguintes funcionalidades:
- Buscar livro pelo título: *Permite ao usuário buscar um livro pelo título e armazená-lo no banco de dados.*
- Listar livros registrados: *Lista todos os livros registrados no banco de dados.*
- Listar autores registrados: *Lista todos os autores registrados no banco de dados.*
- Listar autores vivos em um determinado ano: *Lista os autores que estavam vivos em um ano específico e que foram registrados no banco de dados.*
- Listar livros em determinado idioma: *Lista os livros disponíveis no banco de dados em um idioma específico.*
- Listar Top 10 livros mais baixados: *Mostra os 10 livros mais baixados no Gutendex.*
- Buscar autor: *Permite ao usuário buscar informações sobre um autor. Caso o autor não seja encontrado no banco de dados, será realizada uma busca de um livro de sua autoria que será inserido no banco de dados.*
- Verificar percentual de livros por idioma: *Mostra o percentual de livros no banco de dados em cada idioma.*

## Tecnologias utilizadas
- Java
- Spring Boot
- Maven
- MySQL

## Instalação
Para executar a aplicação localmente, siga estas etapas:
1. Clone este repositório: git clone https://github.com/iacoleite/forumhub.git
2. Certifique-se de ter o Java JDK 17 ou superior instalado.
3. Importe o projeto em sua IDE preferida.
4. Configure o MySQL e atualize as configurações no arquivo application.properties.
5. Execute a classe ForumHubApplication.java.

### Uso
Após iniciar a API será possível acessar os diversos endpoints utilizados para gerenciar os usuários, o login, os tópicos e as respostas.
Para facilitar a compreensão dos diversos endpoints da API, foi utilizado o Springdoc OpenAPI. Dessa forma é possível acessar a documentação da API por meio do SwaggerUI:
- Abra seu navegador e vá para http://localhost:8080/swagger-ui.html
- No SwaggerUI estão listados os endpoints documentados com detalhes sobre os parâmetros de entrada e saída.
- Por meio do SwaggerUI é possível, também, testar todos os endpoints através do browser, enviando requisições de teste e as respostas correspondentes.

### Contribuição
Sinta-se à vontade para abrir um pull request ou uma issue.

### Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo LICENSE para mais detalhes.


