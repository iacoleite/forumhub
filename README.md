# Literalura

## Descrição
Projeto desenvolvido no programa ONE - Oracle Next Education realizado pela Alura em parceria com a Oracle.
O projeto foi desenvolvido em Java 17 no decorrer da especialização em back-end. 
O ForumHub é uma API que permite aos usuários interagirem entre si por meio da criação, atualização, exclusão e visualização de tópicos e respostas em um fórum de discussão.
Sua construção segue uma arquitetura RESTful e foi realizado utilizando Spring Boot.

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


