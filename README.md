# 🎬 MovieApi

**MovieApi** é uma aplicação desenvolvida em **Java com Spring Boot** para gerenciamento de filmes.  
O objetivo é permitir que usuários cadastrem, filtrem e gerenciem sua lista de filmes de forma prática e segura.

## 🚀 Funcionalidades
- Autenticação e autorização com **Spring Security**
- Cadastro e gerenciamento de usuários
- CRUD completo de filmes (inserir, listar, atualizar e deletar)
- Filtros personalizados (ex.: busca por gênero)
- Tratativas de exceções com **GlobalExceptionHandler**
- Retorno amigável para listas vazias (`"Your List is empty"`)

## 🛠️ Tecnologias utilizadas
- **Java 17**
- **Spring Boot** (Web, Security, Validation)
- **JPA/Hibernate** para persistência
- **Banco de dados H2/PostgreSQL**
- **REST API** com respostas estruturadas em JSON

## 📂 Estrutura do projeto
src/  
├── main/ │  
├── java/com/domingostec/MovieApi/ │  
│ ├── controller/ # Endpoints REST │  
│ ├── service/ # Regras de negócio │  
│ ├── repository/ # Acesso ao banco   
│ │ ├── exceptions/ # Tratativas de erro │  
│ └── model/ # Entidades │  
└── resources/ │  
├── application.properties │  
└── data.sql # Dados iniciais (opcional)

## ▶️ Como executar
1. Clone o repositório:  
   ```bash  
   git clone https://github.com/domingostec/MovieApi.git
    
2. Entre na pasta do projeto:
   ```bash 
   cd MovieApi  

3. Execute com Maven: 
   ```bash
   mvn spring-boot:run  


Acesse no navegador:
http://localhost:8080/movies/MyList

📌 Exemplos de uso

Listar filmes do usuário logado : 
`GET /movies/MyList`

Filtrar por gênero :
`GET /movies/MyList/genre?genre=Action`

Resposta quando lista está vazia : 
   ```json
{  
  "message": "Your List is empty",  
  "movies": []  
}  

##👨‍💻 Autor
Projeto desenvolvido por Matheus Domingos
🔗 GitHub: domingostec/MovieApi
