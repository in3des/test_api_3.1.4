package api.test.test_api_314;

import api.test.test_api_314.Entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");


//        //first request
//        RestTemplate template = new RestTemplate();
//        ResponseEntity<String> forEntity = template.getForEntity("http://google.bg", String.class);
//        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
//
//        //subsequent request
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        restTemplate.exchange("http://url", HttpMethod.POST, entity, String.class);


//        Список URL для операций и типы запросов:
//
//        Получение всех пользователей - …/api/users ( GET )
//        Добавление пользователя - …/api/users ( POST )
//        Изменение пользователя - …/api/users ( PUT )
//        Удаление пользователя - …/api/users /{id} ( DELETE )


        String API_URL = "http://91.241.64.178:7081/api/users";

        //first request
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> forEntity = template.getForEntity(API_URL, String.class);

        String result = template.getForObject(API_URL, String.class);

//        System.out.println("getEntity = " + forEntity);
//        System.out.println("getObject = " + result);

        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
//        forEntity.getHeaders().getFirst("Set-Cookie");

        StringBuilder jsId = new StringBuilder();
        jsId.append(forEntity.getHeaders().get("Set-Cookie").toString());
//        System.out.println("jsId - " + jsId);
        int indexFirst = jsId.indexOf("=");
        int indexLast = jsId.indexOf(";");
//        System.out.println(indexFirst + " " + indexLast);
//        String JSESSIONID = jsId.substring(indexFirst+1, indexLast);
        String JSESSIONID = jsId.substring(1, indexLast);
        System.out.println("JSESSIONID ---> " + JSESSIONID);

//        {"id":3,"name":"James","lastName":"Brown","age":37}
//        {"id":3,"name":"Thomas","lastName":"Shelby","age":37}




//        //subsequent request
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", JSESSIONID);
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.POST, entity, String.class);




        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();


        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", JSESSIONID);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        // HttpEntity<String>: To get result as String.
//        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // RestTemplate
//        RestTemplate restTemplate = new RestTemplate();


//        ResponseEntity<String> response22 = template.exchange(API_URL, //
//                HttpMethod.GET, entity, String.class);
//
//        String result22 = response22.getBody();
//
//        System.out.println("response entity getBody = " + result22);





//---------------------------------------------------------------------------------------



        Long id = 3L;

        User userNewThree = new User(id, "James", "Brown", (byte) 16);
        User userNewThreeChange = new User(id, "Thomas", "Shelby", (byte) 16);

        HttpEntity<String> requestBody33 = new HttpEntity<>(userNewThree.toString(), headers);
        HttpEntity<String> requestBody44 = new HttpEntity<>(userNewThreeChange.toString(), headers);


        ResponseEntity<String> response33 = template.exchange(API_URL, //
                HttpMethod.POST, requestBody33, String.class);

        String result33 = response33.getBody();

        System.out.println("new code 1 = " + result33);

        ResponseEntity<String> forEntity2 = template.getForEntity(API_URL, String.class);
        forEntity2.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);



        ResponseEntity<String> response44 = template.exchange(API_URL, //
                HttpMethod.PUT, requestBody44, String.class);

        String result44 = response44.getBody();

        System.out.println("new code 2 = " + result44);


        ResponseEntity<String> response55 = template.exchange(API_URL+"/"+id, //
                HttpMethod.DELETE, requestBody44, String.class);

        String result55 = response55.getBody();

        System.out.println("new code 3 = " + result55);

        System.out.println(result33+result44+result55);

    }



}
