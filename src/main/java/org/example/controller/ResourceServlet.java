package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.example.model.Audio;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

//@WebServlet(name = "skiiers", value = "skiiers")
@WebServlet(name = "audios", value = "audios/*")
public class ResourceServlet extends HttpServlet {
    
	
	/*
	 * ConcurrentHashMap is thread safe; 
	 */
	ConcurrentHashMap<String, Object> audioDB = new ConcurrentHashMap<>();
	
	/*
	 * simply emulation of in memory database;  
	 */
	@Override
	 public void init() throws ServletException {
		audioDB.put("audio_1", new Audio("1","50 cents","Candy Shop","Candy Shop","12",1991,"12321312",10));
		audioDB.put("audio_2", new Audio("2","coldplay","Candy Shop","Fix you","12",1992,"12321312",10));
		 
	 }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
//		System.out.println(pathInfo);
		if (pathInfo == null) {
			
			List<Object> audios = audioDB.entrySet().stream()
									.filter(entry -> entry.getKey().matches("audio.*"))
									.map(Map.Entry::getValue)
									.collect(Collectors.toList());
			
			Gson gson = new Gson();
		    JsonElement element = gson.toJsonTree(audioDB);

		    PrintWriter out = response.getWriter();
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
//		    out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(audio));
		    out.println("GET RESPONSE IN JSON - all elements " + element.toString());
		    out.flush();
        } else {

        	String propertyName = pathInfo.substring(1);
        	System.out.println(pathInfo);
     		Audio audio = (Audio) audioDB.get("audio_1");
     		String propertyValue = "";
     		
      		if("id".equals(propertyName)) propertyValue = audio.getId();
     		else if("name".equals(propertyName)) propertyValue = audio.getName();
     		else if("track_title".equals(propertyName)) propertyValue = audio.getTitle();
     		else if("album_title".equals(propertyName)) propertyValue = audio.getAlbum_Title();
     		else if("track_number".equals(propertyName)) propertyValue = audio.getTrack_Number();
     		else if("year".equals(propertyName)) propertyValue = Integer.toString(audio.getYear());
     		else if("reviews".equals(propertyName)) propertyValue = audio.getReviews();
     		else if("sold".equals(propertyName)) propertyValue = Long.toString(audio.getSold());

     		PrintWriter out = response.getWriter();
     		response.setContentType("application/json");
     		response.setStatus(HttpServletResponse.SC_OK);
     		response.setCharacterEncoding("UTF-8");
             
            out.println(propertyValue);
            out.flush();
        }

        

		
		
		//		String id = request.getParameter("id");
//		Audio audio  = (Audio) audioDB.get(id);
		
//		Artist art = new Artist();
//		art.setId(id);
//		art.setName(name);
//		
//	    Gson gson = new Gson();
//	    JsonElement element = gson.toJsonTree(audioDB);
	    
	    /*
	     * response in normal string message;
	     */
		//response.getOutputStream().println("Artist id is " + id +" name is " + name);
    
		
		/*
		 * response in json with as a data model
		 */
//		PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(audio));
//        
//        out.println("GET RESPONSE IN JSON - all elements " + element.toString());
//     
//        out.flush();   
	
	}
	
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               
	  String id =  request.getParameter("id");
      String artistName = request.getParameter("name");
      String albumTitle = request.getParameter("album_title");
      String trackTitle = request.getParameter("track_title");
      String trackNumber = request.getParameter("track_number");
      int year = Integer.parseInt(request.getParameter("year"));
      String number_of_reviews = request.getParameter("reviews");
      int sold_copies = Integer.parseInt(request.getParameter("sold"));
      
      try {
          year = Integer.parseInt(request.getParameter("year"));
          sold_copies = Integer.parseInt(request.getParameter("sold"));
      } catch (NumberFormatException e) {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid year parameter");
          return;
      }
      
      
        
        Audio audio = new Audio(id, artistName, trackTitle, albumTitle, trackNumber, year, number_of_reviews, sold_copies);
	
        audioDB.put(id, audio);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setStatus(200);
	
	
	
	
	
	
	
	
	

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       
    	
//    	String id =  request.getParameter("id");
//        String artistName = request.getParameter("name");
//        String albumTitle = request.getParameter("album_title");
//        String trackTitle = request.getParameter("track_title");
//        String trackNumber = request.getParameter("track_number");
//        int year = Integer.parseInt(request.getParameter("year"));
//        String number_of_reviews = request.getParameter("reviews");
//        int sold_copies = Integer.parseInt(request.getParameter("sold"));
  //      
//        Audio audio = new Audio(id, artistName, trackTitle, albumTitle, trackNumber, year, number_of_reviews, sold_copies);
        
//        audioDB.put(id, audio);
//        response.setStatus(200);
    	
    	
    	
    	
    	
//    	
//    	String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        
//        
//        
//        artistDB.put(id, name);
//        response.setStatus(200);
//    	
//    	response.getOutputStream().println("POST RESPONSE: Artist " + name + " is added to the database.");
    }}

