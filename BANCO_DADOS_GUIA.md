# 🎵 Vinis Web - Guia de Configuração do Banco de Dados

## 📋 Índice
1. [Desenvolvimento (H2)](#desenvolvimento-h2)
2. [Produção (MySQL)](#produção-mysql)
3. [Scripts SQL](#scripts-sql)

---

## 🚀 Desenvolvimento (H2)

### Descrição
A aplicação vem configurada para usar um banco de dados **H2 em memória** no ambiente de desenvolvimento. Não requer instalação de MySQL.

### Como Rodar
```bash
# Terminal PowerShell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
.\mvnw spring-boot:run -Dspring-boot.run.arguments='--spring.profiles.active=dev'
```

### Características
- ✅ Zero setup - não precisa instalar MySQL
- ✅ Dados de teste pré-carregados automaticamente
- ✅ Perfeito para desenvolvimento local
- ❌ Dados perdidos ao reiniciar a aplicação

### Acesso
- **Aplicação**: http://localhost:8081
- **H2 Console**: http://localhost:8081/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (deixar em branco)

---

## 🗄️ Produção (MySQL)

### Pré-requisitos
1. **MySQL Server** instalado e rodando
2. **MySQL Workbench** ou **DBeaver** (opcional, para visualizar dados)

### Instalação do MySQL

#### Windows
1. Baixar em: https://dev.mysql.com/downloads/mysql/
2. Executar instalador e seguir o setup padrão
3. Usar usuário `root` com senha vazia (ou a senha que você escolher)
4. Iniciar o serviço MySQL:
   ```bash
   # PowerShell como Administrador
   Start-Service MySQL80  # ou versão do seu MySQL
   ```

### Criar o Banco de Dados

#### Opção 1: MySQL Command Line
```bash
# Abrir MySQL CLI
mysql -u root -p

# Colar todo o conteúdo do arquivo CRIAR_BANCO_MYSQL.sql
# (Copie e cole no terminal MySQL)
```

#### Opção 2: MySQL Workbench
1. Abrir MySQL Workbench
2. Conectar com `root` / (senha)
3. File → Open SQL Script → Selecionar `CRIAR_BANCO_MYSQL.sql`
4. Executar (Ctrl+Shift+Enter)

#### Opção 3: DBeaver
1. Abrir DBeaver
2. Conectar ao MySQL
3. Right-click na conexão → SQL Editor → Open SQL Script
4. Selecionar `CRIAR_BANCO_MYSQL.sql`
5. Executar (Ctrl+Enter)

### Como Rodar com MySQL
```bash
# Terminal PowerShell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
.\mvnw clean install -DskipTests
.\mvnw spring-boot:run -Dspring-boot.run.arguments='--spring.profiles.active=prod'
```

### Características
- ✅ Dados persistidos no MySQL
- ✅ Pronto para ambiente de produção
- ✅ Performance otimizada para volume de dados
- ⚠️ Requer MySQL Server instalado e rodando

### Acesso
- **Aplicação**: http://localhost:8080

---

## 📄 Scripts SQL

### Arquivo: `CRIAR_BANCO_MYSQL.sql`

Este arquivo contém:

#### 1. **Criação do Banco de Dados**
```sql
CREATE DATABASE IF NOT EXISTS vinisweb 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

#### 2. **Criação das Tabelas**
- `cliente` - Clientes da loja
- `vinil` - Produtos (vinis)
- `funcionario` - Funcionários
- `compra` - Pedidos
- `item_compra` - Itens de cada pedido

#### 3. **Dados de Teste (Opcional)**
O arquivo contém comentários com dados de teste que podem ser descomentados.

### Tabelas Criadas

```
cliente
├── id (PK)
├── cpf (UNIQUE)
├── email
├── nome
└── tipo_cliente (Regular/VIP)

vinil
├── id (PK)
├── codigo (UNIQUE)
├── titulo
├── artista
├── genero
├── preco_venda
└── qtd_disponivel

funcionario
├── id (PK)
├── cpf (UNIQUE)
├── nome
├── email
├── cargo
└── salario

compra
├── id (PK)
├── cliente_id (FK → cliente)
├── data_compra
└── valor_total

item_compra
├── id (PK)
├── compra_id (FK → compra)
├── vinil_id (FK → vinil)
├── quantidade
└── valor_item
```

---

## 🔄 Alterar Perfil de Execução

### De Desenvolvimento para Produção
```bash
# Parar a aplicação (Ctrl+C)
# Executar:
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
.\mvnw spring-boot:run -Dspring-boot.run.arguments='--spring.profiles.active=prod'
```

### De Produção para Desenvolvimento
```bash
# Parar a aplicação (Ctrl+C)
# Executar:
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
.\mvnw spring-boot:run -Dspring-boot.run.arguments='--spring.profiles.active=dev'
```

---

## 🐛 Troubleshooting

### Erro: "Access denied for user 'root'@'localhost'"
**Solução**: Atualizar a senha no `application-prod.properties`
```properties
spring.datasource.username=root
spring.datasource.password=suaSenha  # Adicionar a senha aqui
```

### Erro: "Can't connect to MySQL server"
**Solução**: Verificar se MySQL está rodando
```bash
# PowerShell
Get-Service MySQL80  # Verificar status
Start-Service MySQL80  # Iniciar se parado
```

### Erro: "Database 'vinisweb' doesn't exist"
**Solução**: Executar o script `CRIAR_BANCO_MYSQL.sql` novamente

### H2 Console não funciona em produção
**Esperado**: H2 console está desabilitado em produção (segurança)

---

## 📊 Visualizar Dados

### Em Desenvolvimento (H2)
```
http://localhost:8081/h2-console
```

### Em Produção (MySQL)
Use **MySQL Workbench** ou **DBeaver** conectando com:
- Host: `localhost`
- Port: `3306`
- User: `root`
- Password: (sua senha)
- Database: `vinisweb`

---

## ✅ Checklist de Configuração

### Para Desenvolvimento
- [ ] Java 21 instalado
- [ ] Maven instalado
- [ ] Clonar repositório
- [ ] Rodar `mvnw spring-boot:run -Dspring-boot.run.arguments='--spring.profiles.active=dev'`

### Para Produção
- [ ] Java 21 instalado
- [ ] Maven instalado
- [ ] MySQL instalado e rodando
- [ ] Executar `CRIAR_BANCO_MYSQL.sql` no MySQL
- [ ] Atualizar senha em `application-prod.properties` se necessário
- [ ] Rodar `mvnw spring-boot:run -Dspring-boot.run.arguments='--spring.profiles.active=prod'`

---

## 📞 Suporte

Para dúvidas sobre configuração de banco de dados, consulte:
- [Spring Boot MySQL Guide](https://spring.io/guides/gs/accessing-data-mysql/)
- [Hibernate Documentation](https://hibernate.org/)
- [MySQL Official Documentation](https://dev.mysql.com/doc/)
