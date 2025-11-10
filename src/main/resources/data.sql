-- Pagamento AUTORIZADO
INSERT INTO TRANSACAO (
    id,
    cartao,
    valor,
    data_hora,
    estabelecimento,
    nsu,
    codigo_autorizacao,
    status,
    tipo,
    parcelas
) VALUES (
             '100023568900001',
             '4444********1234',
             500.50,
             TIMESTAMP '2021-05-01 18:30:00',
             'PetShop Mundo cão',
             '1234567890',
             '147258369',
             'AUTORIZADO',
             'AVISTA',
             1
         );

-- Pagamento CANCELADO
INSERT INTO TRANSACAO (
    id,
    cartao,
    valor,
    data_hora,
    estabelecimento,
    nsu,
    codigo_autorizacao,
    status,
    tipo,
    parcelas
) VALUES (
             '100023568900002',
             '5555********5678',
             300.00,
             TIMESTAMP '2021-05-02 10:15:00',
             'Supermercado do Zé',
             '0987654321',
             '258369147',
             'CANCELADO',
             'PARCELADO_LOJA',
             2
         );
