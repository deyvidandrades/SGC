package main.java.interfaces;

import main.res.valores.Strings;

public interface UriImagem {

    default String getURI(int imagem) {

        switch (imagem) {
            case 1:
                return Strings.IMG_1;
            case 2:
                return Strings.IMG_2;
            case 3:
                return Strings.IMG_3;
            case 4:
                return Strings.IMG_4;
            case 5:
                return Strings.IMG_5;
            case 6:
                return Strings.IMG_6;
            case 7:
                return Strings.IMG_7;
            case 8:
                return Strings.IMG_8;
            case 9:
                return Strings.IMG_9;
            case 10:
                return Strings.IMG_10;
            default:
                return Strings.ICONE;
        }
    }
}
