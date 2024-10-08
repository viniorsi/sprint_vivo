package com.vivo.territory.Domain.User.Enums;

public enum StatusEnum {


        A( "Ativo"),
        I( "Inativo"),
        P( "Pendente");

        private String descricao;

        StatusEnum(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static String getDescricaoByLetra(String letra) {
        for (StatusEnum status : values()) {
            if (status.name().equals(letra)) {
                return status.getDescricao();
            }
        }
        throw new IllegalArgumentException("Status não encontrado para a letra: " + letra);
    }

}



