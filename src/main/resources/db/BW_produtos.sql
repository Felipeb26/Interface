CREATE TABLE IF NOT EXISTS produtos (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  idUsuario INTEGER,
  nome VARCHAR(255),
  descricao VARCHAR(255),
  valor DECIMAL(10, 2)
);

INSERT INTO produtos (nome, descricao, valor)
SELECT nome, descricao, valor FROM (
    SELECT 'Arroz' AS nome, 'Arroz branco, tipo longo, pacote de 5kg' AS descricao, 10.50 AS valor UNION ALL
    SELECT 'Feijão' AS nome, 'Feijão preto, tipo carioca, pacote de 1kg' AS descricao, 15.75 AS valor UNION ALL
    SELECT 'Macarrão' AS nome, 'Espaguete nº 7, pacote de 500g' AS descricao, 20.25 AS valor UNION ALL
    SELECT 'Óleo de Soja' AS nome, 'Óleo de soja refinado, garrafa de 900ml' AS descricao, 8.99 AS valor UNION ALL
    SELECT 'Açúcar' AS nome, 'Açúcar refinado, pacote de 1kg' AS descricao, 12.00 AS valor UNION ALL
    SELECT 'Café' AS nome, 'Café torrado e moído, embalagem a vácuo, 500g' AS descricao, 18.50 AS valor UNION ALL
    SELECT 'Leite' AS nome, 'Leite integral pasteurizado, caixa de 1 litro' AS descricao, 25.99 AS valor UNION ALL
    SELECT 'Farinha de Trigo' AS nome, 'Farinha de trigo tipo 1, pacote de 1kg' AS descricao, 30.75 AS valor UNION ALL
    SELECT 'Sal' AS nome, 'Sal refinado iodado, pacote de 1kg' AS descricao, 22.00 AS valor UNION ALL
    SELECT 'Biscoitos' AS nome, 'Biscoitos recheados sabor chocolate, pacote de 200g' AS descricao, 16.25 AS valor UNION ALL
    SELECT 'Sabonete' AS nome, 'Sabonete em barra, fragrância de lavanda, unidade' AS descricao, 19.99 AS valor UNION ALL
    SELECT 'Detergente' AS nome, 'Detergente líquido para louças, embalagem de 500ml' AS descricao, 11.50 AS valor UNION ALL
    SELECT 'Shampoo' AS nome, 'Shampoo para todos os tipos de cabelo, frasco de 350ml' AS descricao, 14.25 AS valor UNION ALL
    SELECT 'Condicionador' AS nome, 'Condicionador hidratante, frasco de 300ml' AS descricao, 9.99 AS valor UNION ALL
    SELECT 'Creme Dental' AS nome, 'Creme dental com flúor, embalagem de 90g' AS descricao, 27.00 AS valor UNION ALL
    SELECT 'Papel Higiênico' AS nome, 'Rolo de papel higiênico folha dupla, pacote com 4 rolos' AS descricao, 31.50 AS valor UNION ALL
    SELECT 'Sabão em Pó' AS nome, 'Sabão em pó para máquina de lavar roupa, pacote de 1kg' AS descricao, 24.99 AS valor UNION ALL
    SELECT 'Amaciante' AS nome, 'Amaciante concentrado, fragrância suave, garrafa de 2 litros' AS descricao, 18.75 AS valor UNION ALL
    SELECT 'Desinfetante' AS nome, 'Desinfetante líquido, aroma de pinho, frasco de 500ml' AS descricao, 23.50 AS valor UNION ALL
    SELECT 'Água Sanitária' AS nome, 'Água sanitária concentrada, embalagem de 1 litro' AS descricao, 35.99 AS valor UNION ALL
    SELECT 'Desodorante' AS nome, 'Desodorante roll-on, fragrância neutra, embalagem de 50ml' AS descricao, 40.75 AS valor UNION ALL
    SELECT 'Cereal Matinal' AS nome, 'Cereal matinal sabor chocolate, pacote de 300g' AS descricao, 32.00 AS valor UNION ALL
    SELECT 'Iogurte' AS nome, 'Iogurte natural, garrafa de 500g' AS descricao, 26.25 AS valor UNION ALL
    SELECT 'Margarina' AS nome, 'Margarina cremosa, embalagem de 250g' AS descricao, 29.99 AS valor UNION ALL
    SELECT 'Ovos' AS nome, 'Ovos brancos, dúzia' AS descricao, 21.50 AS valor UNION ALL
    SELECT 'Queijo' AS nome, 'Queijo mussarela fatiado, pacote de 200g' AS descricao, 24.25 AS valor UNION ALL
    SELECT 'Presunto' AS nome, 'Presunto cozido fatiado, pacote de 150g' AS descricao, 19.99 AS valor UNION ALL
    SELECT 'Manteiga' AS nome, 'Manteiga com sal, tablete de 200g' AS descricao, 37.00 AS valor UNION ALL
    SELECT 'Refrigerante' AS nome, 'Refrigerante sabor cola, garrafa de 2 litros' AS descricao, 41.50 AS valor UNION ALL
    SELECT 'Suco' AS nome, 'Suco de laranja natural, garrafa de 1 litro' AS descricao, 34.99 AS valor UNION ALL
    SELECT 'Chocolate' AS nome, 'Barra de chocolate ao leite, 100g' AS descricao, 28.75 AS valor UNION ALL
    SELECT 'Pão' AS nome, 'Pão francês, unidade' AS descricao, 33.50 AS valor UNION ALL
    SELECT 'Geleia' AS nome, 'Geleia de morango, pote de 250g' AS descricao, 45.99 AS valor UNION ALL
    SELECT 'Molho de Tomate' AS nome, 'Molho de tomate pronto, embalagem de 350g' AS descricao, 50.75 AS valor UNION ALL
    SELECT 'Creme de Leite' AS nome, 'Creme de leite em lata, 200g' AS descricao, 42.00 AS valor UNION ALL
    SELECT 'Azeite de Oliva' AS nome, 'Azeite de oliva extra virgem, garrafa de 500ml' AS descricao, 36.25 AS valor UNION ALL
    SELECT 'Vinagre' AS nome, 'Vinagre de álcool, frasco de 500ml' AS descricao, 39.99 AS valor UNION ALL
    SELECT 'Iogurte Grego' AS nome, 'Iogurte grego natural, pote de 150g' AS descricao, 31.50 AS valor UNION ALL
    SELECT 'Cerveja' AS nome, 'Cerveja Pilsen, lata de 350ml' AS descricao, 47.00 AS valor UNION ALL
    SELECT 'Vinho' AS nome, 'Vinho tinto seco, garrafa de 750ml' AS descricao, 52.50 AS valor UNION ALL
    SELECT 'Batata' AS nome, 'Batata inglesa, saco com 5kg' AS descricao, 44.99 AS valor UNION ALL
    SELECT 'Cenoura' AS nome, 'Cenoura orgânica, pacote com 500g' AS descricao, 48.75 AS valor UNION ALL
    SELECT 'Tomate' AS nome, 'Tomate italiano, 1kg' AS descricao, 40.50 AS valor UNION ALL
    SELECT 'Cebola' AS nome, 'Cebola branca, saco com 1kg' AS descricao, 54.99 AS valor UNION ALL
    SELECT 'Alho' AS nome, 'Alho descascado, embalagem de 200g' AS descricao, 60.75 AS valor UNION ALL
    SELECT 'Laranja' AS nome, 'Laranja pera, saco com 2kg' AS descricao, 49.00 AS valor UNION ALL
    SELECT 'Banana' AS nome, 'Banana nanica, dúzia' AS descricao, 43.25 AS valor UNION ALL
    SELECT 'Maçã' AS nome, 'Maçã fuji, saco com 1kg' AS descricao, 46.99 AS valor
) AS tmp WHERE NOT EXISTS (SELECT 1 FROM produtos WHERE produtos.nome = tmp.nome AND produtos.descricao = tmp.descricao AND produtos.valor = tmp.valor);
