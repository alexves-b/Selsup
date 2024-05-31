import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CrptApi {
    private final int requestLimit;
    private final TimeUnit timeUnit;
    private final BlockingQueue<Long> requestTimes;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.requestLimit = requestLimit;
        this.timeUnit = timeUnit;
        this.requestTimes = new LinkedBlockingQueue<>();
    }

    public void createDocument(String documentJson) {
        // Проверяем превышение лимита запросов
        if (requestTimes.size() >= requestLimit) {
            long currentTime = System.currentTimeMillis();
            long earliestRequestTime = currentTime - timeUnit.toMillis(1);
            long timeToRemove;
            while((timeToRemove = requestTimes.poll()) < earliestRequestTime);
        }

        // Отправляем запрос
        String url = "https://ismp.crpt.ru/api/v3/lk/documents/create";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");


        try {
            // Создаем JSON объект для тела запроса
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode requestBody = objectMapper.readValue(documentJson, ObjectNode.class);
            httpPost.setEntity(new StringEntity(requestBody.toString()));

            // Выполняем запрос
            HttpResponse response = httpClient.execute(httpPost);

            // Добавляем время запроса в очередь
            requestTimes.offer(System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}