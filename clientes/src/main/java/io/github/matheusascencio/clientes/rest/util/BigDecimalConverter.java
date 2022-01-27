package io.github.matheusascencio.clientes.rest.util;

import com.ibm.icu.math.BigDecimal;

public class BigDecimalConverter {
    public BigDecimal converter (String v) {
        if(v == null) {
            return null;
        } else {
            return new BigDecimal(v.replace(".", "").replace(",", "."));
        }
    } 
}
