-- ============================================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS VINISWEB (MySQL)
-- ============================================================
-- Crie um novo banco de dados com este script antes de rodar a aplicação
-- Após criar o banco, comente ou remova as linhas de criação para evitar erros

-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS vinisweb 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

-- Usar o banco de dados criado
USE vinisweb;

-- ============================================================
-- TABELA: cliente
-- ============================================================
CREATE TABLE cliente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cpf VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  tipo_cliente VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- TABELA: vinil
-- ============================================================
CREATE TABLE vinil (
  id BIGINT NOT NULL AUTO_INCREMENT,
  artista VARCHAR(255) NOT NULL,
  codigo INT NOT NULL UNIQUE,
  genero VARCHAR(255) NOT NULL,
  preco_venda FLOAT NOT NULL,
  qtd_disponivel INT NOT NULL,
  titulo VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- TABELA: funcionario
-- ============================================================
CREATE TABLE funcionario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cargo VARCHAR(255) NOT NULL,
  cpf VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  salario FLOAT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- TABELA: compra
-- ============================================================
CREATE TABLE compra (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cliente_id BIGINT,
  data_compra DATETIME(6) NOT NULL,
  valor_total FLOAT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- TABELA: item_compra
-- ============================================================
CREATE TABLE item_compra (
  id BIGINT NOT NULL AUTO_INCREMENT,
  compra_id BIGINT,
  quantidade INT NOT NULL,
  valor_item FLOAT NOT NULL,
  vinil_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (compra_id) REFERENCES compra(id),
  FOREIGN KEY (vinil_id) REFERENCES vinil(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- DADOS DE TESTE (OPCIONAL)
-- ============================================================
-- Descomente as linhas abaixo para inserir dados de teste

-- INSERT INTO cliente (cpf, email, nome, tipo_cliente) VALUES
-- ('123.456.789-00', 'joao@email.com', 'João Silva', 'Regular'),
-- ('987.654.321-00', 'maria@email.com', 'Maria Santos', 'VIP'),
-- ('111.222.333-44', 'carlos@email.com', 'Carlos Oliveira', 'Regular');

-- INSERT INTO vinil (artista, codigo, genero, preco_venda, qtd_disponivel, titulo) VALUES
-- ('Michael Jackson', 1001, 'Pop', 150.00, 10, 'Thriller'),
-- ('Pink Floyd', 1002, 'Rock Progressivo', 180.00, 5, 'The Dark Side of the Moon'),
-- ('The Beatles', 1003, 'Rock', 200.00, 8, 'Abbey Road'),
-- ('Fleetwood Mac', 1004, 'Rock', 160.00, 6, 'Rumours');

-- INSERT INTO funcionario (cargo, cpf, email, nome, salario) VALUES
-- ('Vendedor', '555.666.777-88', 'ana@empresa.com', 'Ana Costa', 3000.00),
-- ('Gerente', '999.888.777-66', 'pedro@empresa.com', 'Pedro Mendes', 5000.00),
-- ('Estoquista', '444.333.222-11', 'lucas@empresa.com', 'Lucas Ferreira', 2500.00);

-- INSERT INTO compra (cliente_id, data_compra, valor_total) VALUES
-- (1, NOW(), 350.00),
-- (2, NOW(), 500.00);

-- INSERT INTO item_compra (compra_id, quantidade, valor_item, vinil_id) VALUES
-- (1, 2, 150.00, 1),
-- (1, 1, 50.00, 2),
-- (2, 1, 200.00, 3);

-- ============================================================
-- VERIFICAÇÃO FINAL
-- ============================================================
-- Listar todas as tabelas criadas
SHOW TABLES;

-- Verificar estrutura das tabelas
DESCRIBE cliente;
DESCRIBE vinil;
DESCRIBE funcionario;
DESCRIBE compra;
DESCRIBE item_compra;
