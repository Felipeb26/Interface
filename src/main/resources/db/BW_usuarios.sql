CREATE TABLE IF NOT EXISTS usuarios(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    idade INTEGER,
    nome TEXT,
    email TEXT IDENTITY,
    endereco TEXT,
    senha TEXT,
    adm BIT
);


INSERT INTO usuarios (nome, email, senha, adm)
    SELECT nome, email, senha, adm from (
    SELECT 'adm' as nome, 'adm@gmail.com' as email, '2626' as senha, 1 as adm union all
    select 'user' as nome, 'user@gmail.com' as email , '2626' as senha, 0 as adm
) AS tmp WHERE NOT EXISTS (SELECT 1 FROM usuarios where usuarios.nome = tmp.nome and usuarios.email = tmp.email );