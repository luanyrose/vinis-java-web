-- Criação das tabelas do banco de dados H2

-- Tabela CLIENTE
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    tipo_cliente VARCHAR(255) NOT NULL
);

-- Tabela VINIL
CREATE TABLE IF NOT EXISTS vinil (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    artista VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    codigo INTEGER NOT NULL UNIQUE,
    preco_venda FLOAT NOT NULL,
    qtd_disponivel INTEGER NOT NULL
);

-- Tabela FUNCIONARIO
CREATE TABLE IF NOT EXISTS funcionario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    cargo VARCHAR(255) NOT NULL,
    salario FLOAT NOT NULL
);

-- Tabela COMPRA
CREATE TABLE IF NOT EXISTS compra (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_compra TIMESTAMP NOT NULL,
    cliente_id BIGINT NOT NULL,
    valor_total FLOAT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Tabela ITEM_COMPRA (Join table between COMPRA and VINIL)
CREATE TABLE IF NOT EXISTS item_compra (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantidade INTEGER NOT NULL,
    valor_item FLOAT NOT NULL,
    compra_id BIGINT NOT NULL,
    vinil_id BIGINT NOT NULL,
    FOREIGN KEY (compra_id) REFERENCES compra(id),
    FOREIGN KEY (vinil_id) REFERENCES vinil(id)
);
