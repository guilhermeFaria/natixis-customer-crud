Exercício - deve ser partilhado em GitHub:
Criar um pequeno sistema de CRUD de clientes

Cada cliente deve ter:
•	Nome
•	Data de nascimento
•	Número de identificação fiscal
•	Lista de endereços:
o	Rua
o	Número
o	Complemento
o	Código postal
o	Conselho
o	Distrito
Requisitos Funcionais
•	O cliente deve ter mais de 18 anos
•	O cliente deve ter pelo menos um endereço cadastrado
•	Na tela de cadastro de clientes deve ser possível incluir e excluir endereços
•	Todo endereço deve estar associado a um cliente
•	O preenchimento dos campos de endereço devem ser de acordo com o código postal, sendo esse integrado com a seguinte API:
https://geoapi.pt/docs/
•	Caso o código postal não seja encontrado, o usuário deve ser informado e os campos devem estar habilitados para inserção manual

Requisitos não funcionais:
•	Frontend:   
o	Angular 19
•	Backend:   
o	Java 17
o	Spring Boot
o	Maven
o	Base de dados H2 em memória
o	Testes: unitários e de integração

OBS: Não deve Utilizar IA