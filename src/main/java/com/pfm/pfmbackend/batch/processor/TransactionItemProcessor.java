package com.pfm.pfmbackend.batch.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfm.pfmbackend.batch.dto.TransactionCsvDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component
public class TransactionItemProcessor implements ItemProcessor<TransactionCsvDTO, TransactionCsvDTO> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public TransactionCsvDTO process(TransactionCsvDTO item) throws Exception {
        String description = item.getBhLib();

        try {
            URL url = new URL("http://127.0.0.1:5000/predict");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = String.format("{\"description\":\"%s\"}", description);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            String response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();

            // System.out.println("🧪 API Response: " + response);

            JsonNode jsonNode = mapper.readTree(response);
            JsonNode categoryNode = jsonNode.get("categorie_predite");

            if (categoryNode == null || categoryNode.isNull()) {
                item.setCategorie("INCONNUE");
            } else {
                item.setCategorie(categoryNode.asText());
            }

        } catch (Exception e) {
            System.err.println("❌ Erreur appel API: " + e.getMessage());
            item.setCategorie("ERREUR_API");
        }

        return item;
    }
}


