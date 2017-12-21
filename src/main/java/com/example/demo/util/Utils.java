package com.example.demo.util;

import com.example.demo.entity.ResponseStatus;
import com.example.demo.request.BaseRequest;
import com.example.demo.response.BaseResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Utils {
    public static void waitToResponse() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static ResponseEntity validateRequest(BaseRequest request) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<BaseRequest>> violations = validator.validate(request);
        if(!violations.isEmpty()) {
            BaseResponse res = new BaseResponse();
            res.setMessages(violations.stream().map(v -> v.getMessage()).collect(Collectors.toList()));
            res.setStatus(ResponseStatus.FAILURE);
            return new ResponseEntity(res, HttpStatus.OK);
        }
        return null;
    }


   /* public static LatLng getLongLat(String address, String city, String state, String zipCode) {
        try {
            String urlInfoString = URLEncoder.encode((StringUtils.hasText(address) ? address : "")
                                            + ", "
                                            + (StringUtils.hasText(city) ? city : "")
                                            + " "
                                            + (StringUtils.hasText(state) ? state : "")
                                            + " "
                                            + (StringUtils.hasText(zipCode) ? zipCode : ""),
                                    "UTF-8");

            URL url = new URL("http://www.mapquestapi.com/geocoding/v1/address?key=Fmjtd%7Cluub200rn0%2C85%3Do5-9ursdu&location="+ urlInfoString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");

            InputStream in = connection.getInputStream();

            String encoding = connection.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;

            String body = IOUtils.toString(in, encoding);

            if(!StringUtils.hasText(body)) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(body);
            if(json == null) {
                return null;
            }
            JsonNode jsonResultArray = json.get("results");
            if(jsonResultArray == null || jsonResultArray.get(0) == null || jsonResultArray.get(0).get("locations") == null || jsonResultArray.get(0).get("locations").get(0) == null) {
                return null;
            }
            JsonNode firstLocationLatLong = jsonResultArray.get(0).get("locations").get(0).get("latLng");
            if(firstLocationLatLong == null) {
                return null;
            }
            if(firstLocationLatLong.get("lat") == null || firstLocationLatLong.get("lng") == null) {
                return null;
            }

            return new LatLng(firstLocationLatLong.get("lat").asText(), firstLocationLatLong.get("lng").asText());
        } catch (Exception e) {
            return null;
        }
    }*/


    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        boolean validEmail = matcher.matches();
        return validEmail;
    }

    public static Geometry getGeometry(final double latitude, final double longitude) throws ParseException {
        /*if(!StringUtils.hasText(latitude) || !StringUtils.hasText(longitude)) {
            return null;
        }*/
        return new WKTReader().read("POINT(" + latitude + " " + longitude + ")");
    }

    public static Geometry createCircle(final double x, final double y, double radius) {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(new Coordinate(x, y));//there are your coordinates
        shapeFactory.setSize((radius/1000)*0.018);
        return shapeFactory.createCircle();
    }


    public static float distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);
        return dist;
    }

    public static String getFileExt(String fileNameWithExt) {
        if(fileNameWithExt == null || fileNameWithExt.lastIndexOf(".") == -1) {
            return "";
        }
        return fileNameWithExt.substring(fileNameWithExt.lastIndexOf(".")+1);
    }

    public static boolean isValidDeviceToken(String deviceToken) {
        return deviceToken.length() >= 64;
    }

    public static void main(String[] args) {
        System.out.println("xxxxxxxxxxxxxxxx : " + new String(Charset.forName("UTF-8").encode("K�benhavn V").array()));
    }

    public static int getAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
            age--;
        }

        return age;
    }

}
