# 💰 Expenses Control Service

Uma API REST para controle de gastos pessoais desenvolvida com Spring Boot. Permite gerenciar categorias e transações financeiras com formatação automática em Real brasileiro (BRL).

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Maven**
- **H2 Database** (desenvolvimento)
- **PostgreSQL** (produção)
- **Lombok**

## 📋 Funcionalidades

### 📂 Categorias
- ✅ Criar categoria com nome e cor
- ✅ Listar todas as categorias com transações e metadata
- ✅ Buscar categoria por ID com transações
- ✅ Atualizar categoria
- ✅ Deletar categoria

### 💳 Transações
- ✅ Criar transação vinculada a uma categoria
- ✅ Listar todas as transações com metadata
- ✅ Buscar transação por ID
- ✅ Atualizar transação
- ✅ Deletar transação

### 📊 Metadata
- Total em BRL formatado (R$ 1.234,56)
- Contador de itens
- Relacionamento bidirecional otimizado

## 🛠️ Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+

### Executando localmente
```bash
# Clone o repositório
git clone https://github.com/seu-usuario/Expenses-Control-Service.git

# Entre no diretório
cd Expenses-Control-Service

# Execute a aplicação
./mvnw spring-boot:run

# Ou no Windows
mvnw.cmd spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## 📚 API Endpoints

### Categorias

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/categories` | Lista todas as categorias com transações e metadata |
| `GET` | `/api/categories/{id}` | Busca categoria por ID |
| `POST` | `/api/categories` | Cria nova categoria |
| `PUT` | `/api/categories/{id}` | Atualiza categoria |
| `DELETE` | `/api/categories/{id}` | Remove categoria |

### Transações

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/transactions` | Lista todas as transações com metadata |
| `GET` | `/api/transactions/{id}` | Busca transação por ID |
| `POST` | `/api/transactions` | Cria nova transação |
| `PUT` | `/api/transactions/{id}` | Atualiza transação |
| `DELETE` | `/api/transactions/{id}` | Remove transação |

## 📝 Exemplos de Uso

### Criar uma categoria
```bash
curl -X POST http://localhost:8080/api/categories \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alimentação",
    "color": "#FF5733"
  }'
```

### Criar uma transação
```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Almoço no restaurante",
    "price": 45.90,
    "categoryId": 1
  }'
```

### Listar categorias (resposta)
```json
{
  "categories": [
    {
      "id": 1,
      "name": "Alimentação",
      "color": "#FF5733",
      "transactions": [
        {
          "id": 1,
          "description": "Almoço no restaurante",
          "category": {
            "id": 1,
            "name": "Alimentação",
            "color": "#FF5733"
          },
          "price": "R$ 45,90"
        }
      ],
      "metadata": {
        "total": "R$ 45,90",
        "count": 1
      }
    }
  ],
  "metadata": {
    "total": "R$ 45,90",
    "count": 1
  }
}
```

## 🏗️ Arquitetura

```
src/main/java/com/github/com/ronaldocutrim/Expenses_Control/
├── contracts/           # Exceções customizadas
├── core/
│   ├── category/       # Módulo de categorias
│   │   ├── dto/        # DTOs de entrada/saída
│   │   ├── model/      # Entidade e repositório
│   │   ├── CategoryController.java
│   │   └── CategoryService.java
│   └── transaction/    # Módulo de transações
│       ├── dto/        # DTOs de entrada/saída
│       ├── model/      # Entidade e repositório
│       ├── TransactionController.java
│       └── TransactionService.java
└── ExpensesControlApplication.java
```

## 🔧 Configuração

### Banco de Dados
A aplicação está configurada para usar H2 em desenvolvimento. Para produção, configure PostgreSQL no `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/expenses_control
    username: ${DB_USERNAME:your_username}
    password: ${DB_PASSWORD:your_password}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
```

### Variáveis de Ambiente
- `DB_USERNAME`: Nome de usuário do banco
- `DB_PASSWORD`: Senha do banco

## 🤝 Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Ronaldo Cutrim**
- GitHub: [@ronaldocutrim](https://github.com/ronaldocutrim)
- LinkedIn: [Ronaldo Cutrim](https://linkedin.com/in/ronaldocutrim)