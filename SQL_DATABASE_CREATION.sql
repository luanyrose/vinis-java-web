-- ============================================================
-- SCRIPT SQL COMPLETO - CRIAÇÃO DO BANCO DE DADOS
-- Loja de Vinis - Vinisweb Application
-- Compatível com H2 Database e MySQL
-- ============================================================

-- TABELA: CLIENTE
-- Armazena informações de clientes da loja
CREATE TABLE cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    tipo_cliente VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABELA: VINIL
-- Armazena o catálogo de vinis disponíveis
CREATE TABLE vinil (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    artista VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    codigo INTEGER NOT NULL UNIQUE,
    preco_venda FLOAT NOT NULL,
    qtd_disponivel INTEGER NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABELA: FUNCIONARIO
-- Armazena informações dos funcionários
CREATE TABLE funcionario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    cargo VARCHAR(255) NOT NULL,
    salario FLOAT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABELA: COMPRA
-- Armazena transações de compra
CREATE TABLE compra (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cliente_id BIGINT NOT NULL,
    valor_total FLOAT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABELA: ITEM_COMPRA
-- Armazena os itens (vinis) que fazem parte de cada compra
CREATE TABLE item_compra (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantidade INTEGER NOT NULL,
    valor_item FLOAT NOT NULL,
    compra_id BIGINT NOT NULL,
    vinil_id BIGINT NOT NULL,
    FOREIGN KEY (compra_id) REFERENCES compra(id),
    FOREIGN KEY (vinil_id) REFERENCES vinil(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- DADOS INICIAIS DE TESTE
-- ============================================================

-- INSERT INTO cliente (nome, email, cpf, tipo_cliente) VALUES 
-- ('João Silva', 'joao@example.com', '12345678901', 'Regular'),
-- ('Maria Santos', 'maria@example.com', '98765432109', 'VIP'),
-- ('Carlos Oliveira', 'carlos@example.com', '55555555555', 'Regular');

-- INSERT INTO vinil (titulo, artista, genero, codigo, preco_venda, qtd_disponivel) VALUES 
-- ('Thriller', 'Michael Jackson', 'Pop', 001, 50.00, 10),
-- ('The Dark Side of the Moon', 'Pink Floyd', 'Rock', 002, 65.00, 5),
-- ('Abbey Road', 'The Beatles', 'Rock', 003, 70.00, 8),
-- ('Rumours', 'Fleetwood Mac', 'Rock', 004, 55.00, 12);

-- INSERT INTO funcionario (nome, email, cpf, cargo, salario) VALUES 
-- ('Ana Costa', 'ana@loja.com', '11111111111', 'Vendedor', 2500.00),
-- ('Pedro Mendes', 'pedro@loja.com', '22222222222', 'Gerente', 4000.00),
-- ('Lucas Ferreira', 'lucas@loja.com', '33333333333', 'Estoquista', 2000.00);
