INSERT INTO categoria (id, nome, descricao, data_cadastro, data_atualizacao)
VALUES 
(gen_random_uuid(), 'Alimentação', 'Gastos com alimentos, restaurantes e delivery', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Transporte', 'Gastos com combustível, transporte público e apps de mobilidade', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Moradia', 'Aluguel, condomínio, contas de água, luz e internet', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Lazer', 'Entretenimento, viagens e passeios', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Saúde', 'Medicamentos, plano de saúde e consultas médicas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Educação', 'Cursos, livros e material escolar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Vestuário', 'Roupas, calçados e acessórios', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Outros', 'Gastos diversos não categorizados', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
