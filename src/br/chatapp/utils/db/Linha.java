package br.chatapp.utils.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Linha {
    public List<Entry<Object, Class>> linha;
    public static Map<String, Class> TIPO;

    static {
    	TIPO = new HashMap<String, Class>();

    	TIPO.put("INTEGER", Integer.class);
    	TIPO.put("TINYINT", Byte.class);
    	TIPO.put("SMALLINT", Short.class);
    	TIPO.put("BIGINT", Long.class);
    	TIPO.put("REAL", Float.class);
    	TIPO.put("FLOAT", Double.class);
    	TIPO.put("DOUBLE", Double.class);
    	TIPO.put("DECIMAL", BigDecimal.class);
    	TIPO.put("NUMERIC", BigDecimal.class);
    	TIPO.put("BOOLEAN", Boolean.class);
    	TIPO.put("CHAR", String.class);
    	TIPO.put("VARCHAR", String.class);
    	TIPO.put("TEXT", String.class);
    	TIPO.put("LONGVARCHAR", String.class);
    	TIPO.put("DATE", Date.class);
    	TIPO.put("TIME", Time.class);
    	TIPO.put("TIMESTAMP", Timestamp.class);
    	TIPO.put("SERIAL",Integer.class);
    }

    public Linha() {
    	linha = new ArrayList<Entry<Object, Class>>();
    }

    public <T> void adicionar(T data) {
    	linha.add(new AbstractMap.SimpleImmutableEntry<Object,Class>(data, data.getClass()));
    }

    public void adicionar(Object data, String sqlType) {
        Class tipoCast = Linha.TIPO.get(sqlType.toUpperCase());
        try {
            this.adicionar(tipoCast.cast(data));
        } catch (NullPointerException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(Linha.class.getName());
            lgr.log(Level.SEVERE, e.getMessage()+"Adicione o tipo "+sqlType+" ao TIPO hash map dentro da classe Linha.", e);
            throw e;
        }
    }

    public static void fromarTabela(ResultSet rs, List<Linha> tabela)
            throws SQLException {
        if (rs == null)
            return;

        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();

            int NumOfCol = rsmd.getColumnCount();

            while (rs.next()) {
            	Linha current_row = new Linha();

                for (int i = 1; i <= NumOfCol; i++) {
                    current_row.adicionar(rs.getObject(i), rsmd.getColumnTypeName(i));
                }

                tabela.add(current_row);
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
