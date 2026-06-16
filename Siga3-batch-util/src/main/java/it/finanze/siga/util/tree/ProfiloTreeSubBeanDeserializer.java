package it.finanze.siga.util.tree;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ProfiloTreeSubBeanDeserializer implements JsonDeserializer<ProfiloTreeSubBean> {
	
    public ProfiloTreeSubBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

       String a = json.getAsJsonObject().get("dataScadenza").getAsString();
       String b = json.getAsJsonObject().get("dataScadenzaVisibilita").getAsString();

       SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
//       SimpleDateFormat sdfDateWithTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

       Date date;
       Date date2;
//       Date created;
       try {
          date = sdfDate.parse(a);
          date2 = sdfDate.parse(b);
//          created = sdfDateWithTime.parse(b);
       } catch (ParseException e) {
          throw new RuntimeException(e);
       }

       return new ProfiloTreeSubBean(date,date2);
       
   }

}
