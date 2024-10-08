CREATE TABLE users
(
    id                 bigint PRIMARY KEY AUTO_INCREMENT,
    Cpf                varchar(11) UNIQUE NOT NULL,
    name               varchar(100)       NOT NULL,
    tel                varchar(9),
    ddd                varchar(2),
    email              varchar(30)        NOT NULL,
    password           varchar(255),
    birthday           date,
    status             char(1)
);

CREATE TABLE UsersVerification
(
    id                bigint PRIMARY KEY AUTO_INCREMENT,
    user_id           bigint NOT NULL,
    verification_code varchar(255)  NOT NULL,
    expiration_date   date,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE OptCodeVerifications
(
    id                bigint PRIMARY KEY AUTO_INCREMENT,
    user_id           bigint NOT NULL,
    verification_code varchar(255)  NOT NULL,
    expiration_date   date,
    FOREIGN KEY (user_id) REFERENCES users (id)
);


CREATE TABLE html_templates
(
    id      bigint AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    content TEXT         NOT NULL
);

INSERT INTO html_templates (name, content)
VALUES ('welcome_email', '<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-vindo ao Vivo App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .header {
            text-align: center;
            padding: 10px 0;
        }
        .header img {
            width: 100px;
        }
        .header h1 {
            margin: 0;
            color: #4a148c;
        }
        .content {
            padding: 20px;
            color: #555555;
            line-height: 1.6;

        }
        .content h2 {
            color: #4a148c;
        }
        .promotion {
            text-align: center;
            margin: 20px 0;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 10px;
        }
        .promotion h3 {
            color: #4a148c;
        }
        .promotion h2 {
            color: #4a148c;
        }
        .footer {
            text-align: center;
            padding: 10px 0;
            color: #999999;
            font-size: 14px;
        }
        .footer a {
            color: #4a148c;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Bem-vindo ao Vivo App!</h1>
        </div>
        <div class="content">
            <p>Estamos muito felizes por tê-lo conosco. No Vivo App, você tem tudo o que precisa na palma da sua mão! Explore nossos serviços, aproveite ofertas exclusivas e simplifique sua vida com a Vivo.</p>
            <div class="promotion">
                <h3>Seu código de verificação:</h3>
                <h2>1 2 3 4 5 6</h2>
            </div>
            <p>Se precisar de ajuda, nossa equipe está à disposição para garantir que você aproveite ao máximo todos os benefícios.</p>
        </div>
    </div>
</body>
</html>
');

INSERT INTO html_templates (name, content)
VALUES ('Verification Email', '<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email de Verificação</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 100%;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .email-wrapper {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .header {
            background-color: #4b0082; /* Roxo Vivo */
            color: #ffffff;
            padding: 20px;
            text-align: center;
        }
        .content {
            padding: 40px 20px;
            text-align: center;
            color: #333333;
        }
        .verification-code {
            font-size: 36px;
            font-weight: bold;
            color: #4b0082; /* Roxo Vivo */
            margin: 20px 0;
        }
        .footer {
            background-color: #e0115f; /* Rosa Vivo */
            color: #ffffff;
            padding: 15px;
            text-align: center;
        }
        .footer-text {
            color: #ffffff;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="email-wrapper">
            <!-- Header -->
            <div class="header">
                <h1>VIVO</h1>
            </div>

            <!-- Content -->
            <div class="content">
                <p>Aqui está seu código de verificação:</p>
                <div class="verification-code">1 2 3 4 5 6</div>
            </div>

            <!-- Footer -->
            <div class="footer">
                <p class="footer-text">Se você não solicitou este código, por favor ignore este email.</p>
            </div>
        </div>
    </div>
</body>
</html>
');

