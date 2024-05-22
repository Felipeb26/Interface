package com.batsworks.interfaces.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Currency {

    public static String formatToBrazilianCurrency(BigDecimal amount) {
        // Configurar o símbolo e separadores para o formato brasileiro
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setCurrencySymbol("R$");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        // Criar o formato com base nos símbolos configurados
        DecimalFormat decimalFormat = new DecimalFormat("¤ #,##0.00", symbols);

        // Formatar o BigDecimal para uma string de moeda brasileira
        return decimalFormat.format(amount);
    }
}
