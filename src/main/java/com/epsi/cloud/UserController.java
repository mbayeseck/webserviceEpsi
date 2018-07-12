package com.epsi.cloud;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epsi.cloud.security.WebSecurityConfig;

@Controller
@RestController
public class UserController  {

	private ServiceUsersInterface serviceUsersInterface;
	private ServiceCommentInterface serviceCommentInterface;
	private ServiceArticlesInterface serviceArticlesInterface;

	@Autowired
	public UserController(ServiceUsersInterface serviceUsersInterface,
			ServiceCommentInterface serviceCommentInterface, ServiceArticlesInterface serviceArticlesInterface) {
		super();
		this.serviceUsersInterface = serviceUsersInterface;
		this.serviceCommentInterface = serviceCommentInterface;
		this.serviceArticlesInterface = serviceArticlesInterface;
	}
	

    
    public List<Users> getUser(int id){
    	  List<Users> user = serviceUsersInterface.findById(id);
    	  return user;
    }
     // Récupération de commentaire
     @RequestMapping(value = "/articles/{article}/comments", method = RequestMethod.GET)
     public @ResponseBody String getComment(@PathVariable("article") String id){
    	String jsonObject = "";
    	List<Comments> comments = serviceCommentInterface.findByArticle(id);   	
    	if (comments != null){
    		//Si la liste contient un seul élément : Single Comment
    		if (comments.size() == 1){
    			jsonObject = "{\"comment\":"
    					+ "{\"id\":"+comments.get(0).getId()+","
    					+ " \"createdAt\":\""+comments.get(0).getCreated_at()+"\","
    					+ "\"updatedAt\":\""+comments.get(0).getUpdated_at()+"\","
    					+ "\"body\":\""+comments.get(0).getBody()+"\","
    					+ "\"author\":"
    						+ "{\"username\":\""+getUser(comments.get(0).getUser_id()).get(0).getUsername()+"\","
    						+ "\"bio\":\""+getUser(comments.get(0).getUser_id()).get(0).getBio()+"\","
    						+ "\"image\":\""+getUser(comments.get(0).getUser_id()).get(0).getImage()+"\","
    						+ "\"following\":false}}"
    	                + "}";
    		}
    		//Si non plusieurs commentaires, il faudra parcourir la liste et former le JSON
    		else if(comments.size() > 1){
    			jsonObject = "{\"comments\":";
    			System.out.println("Je trouve ici "+ comments.size()+" d'articles !");
    			for (int i = 0; i < comments.size(); i++) {
    				jsonObject = jsonObject     						
        					+ "[{\"id\":"+comments.get(i).getId()+","
        					+ " \"createdAt\":\""+comments.get(i).getCreated_at()+"\","
        					+ "\"updatedAt\":\""+comments.get(i).getUpdated_at()+"\","
        					+ "\"body\":\""+comments.get(i).getBody()+"\","
        					+ "\"author\":"
        						+ "{\"username\":\""+getUser(comments.get(i).getUser_id()).get(0).getUsername()+"\","
        						+ "\"bio\":\""+getUser(comments.get(i).getUser_id()).get(0).getBio()+"\","
        						+ "\"image\":\""+getUser(comments.get(i).getUser_id()).get(0).getImage()+"\","
        						+ "\"following\":\"false\"}}]";        	                
				}
    			jsonObject = jsonObject + "}";
    		} 
    		else
    			jsonObject = "{\"Erreur\":\"Pas de Commentaires pour cet article\"" +
                        "}";	
    		System.out.println("pas de commentaires trouvés pour cet article !");
    	}
    	else {
    		jsonObject = "{\"Erreur\":\"Pas de Commentaires trouvés\"" +
                    "}";
    	}
		return jsonObject;
		
     }
     
     //Ajouter commentaire
    // @PostMapping(value = "/articles/{slug}/comments")
     @RequestMapping(value = "/articles/{slug}/comments", headers = "Accept=application/json", method = RequestMethod.POST)
     public ResponseEntity<?> addComment(@PathVariable("slug") String id,
    		 Principal principal,
    		 @RequestBody HashMap<String, HashMap<String, String>> requestData
    		 ){
    	 HashMap<String, String> commentInfo = requestData.get("comment");
    	 String body = commentInfo.get("body");
    	 String  jsonComment = "";
    	 List<Articles> article = serviceArticlesInterface.findBySlug(id);
    	 Users user = serviceUsersInterface.findByUsername(principal.getName());
    	if(article !=null && user != null){
    	 if(article.size() == 1){
    		 Comments comment= new Comments(user.getId(), article.get(0).getSlug(),body, new Date(), new Date());
    		 serviceCommentInterface.save(comment);
    		 jsonComment = "{\"comment\":"
 					+ "{\"id\":"+comment.getId()+","
 					+ " \"createdAt\":\""+comment.getCreated_at()+"\","
 					+ "\"updatedAt\":\""+comment.getUpdated_at()+"\","
 					+ "\"body\":\""+comment.getBody()+"\","
 					+ "\"author\":"
 						+ "{\"username\":\""+user.getUsername()+"\","
 						+ "\"bio\":\""+user.getBio()+"\","
 						+ "\"image\":\""+user.getImage()+"\","
 						+ "\"following\":false}}"
 	                + "}";
    		 
    	 }
    	 else if (article.size() > 1) {
    		 jsonComment = jsonComment + "{\"Erreur\":\"Plusieurs articles de meme identifiant trouvés, Verifier les articles avant d'ajouter un commentaire !\"" +
	                    "}";
    		 System.out.println("Plusieurs articles de meme slug");
 	 	 } 
    	 else {
    		 jsonComment = jsonComment + "{\"Erreur\":\" Cet article n'existe pas !\"" +
	                    "}";
 		 System.out.println("Pas d'articles");
    	 }
    	}
    	else {
   		 jsonComment = jsonComment + "{\"Erreur\":\" Cet article null !\"" +
                 "}";
	 System.out.println("article null");
	 }
    		
    		 
    	
    	 return ResponseEntity.status(200).body(jsonComment);   	 
     }
     
     //Supprimer commentaire 
     @RequestMapping(value = "/articles/{slug}/comments/{id}", method = RequestMethod.DELETE)
     public @ResponseBody String deleteComment(@PathVariable("slug") String slug,
    		 @PathVariable("id") int id,
    		 @AuthenticationPrincipal Users user){
    	 String reponseJson = "";
    	 List<Articles> article = serviceArticlesInterface.findBySlug(slug);
    	if(article !=null){
    	 if(article.size() == 1){
    		 
    		 List<Comments> comment = serviceCommentInterface.findByArticleAndId(article.get(0).getSlug(), id);
    		 if(comment.size() ==1) {
    			 serviceCommentInterface.delete(comment.get(0)); 
    		 reponseJson = reponseJson + "{\"Status\":{\"OK\":\"Suppression effetuée avec succès\"" +
                     "}";
    		 }
    		 else if (comment.size() > 1) {
    			 reponseJson = reponseJson + "{\"Status\":{\"Erreur\":\"On ne peut pas avoir plusieurs commentaire pour un id\"" +
    	                    "}";
    			 System.out.println("On ne peut pas avoir plusieurs commentaire pour un id");
    		 }
    		 else 
    		 {
    			 reponseJson = reponseJson + "{\"Status\":{\"Erreur\":\"Id de ce commentaire n'existe pas !\"" +
    	                    "}";
    			 System.out.println("Id de ce commentaire n'existe pas !");
    		 }
    	 }
    	 
    	else if(article.size() > 1){
    	    reponseJson = reponseJson + "{\"Status\":{\"Erreur\":\"Plusieurs articles detectes\"" +
    		                    "}";
    	    System.out.println("Plusieurs articles");
    	     }
    	 else {
    		 reponseJson = reponseJson + "{\"Status\":{\"Erreur\":\"Pas d'articles\"" +
	                    "}";
    		 System.out.println("Pas d'articles");
    	 }
    	}
    	else {
   		 reponseJson = reponseJson + "{\"Status\":{\"Erreur\":\"articles null\"" +
                 "}";
		 System.out.println("articles null");
	 } 	 
    	 return reponseJson;   	 
     }
	public ServiceUsersInterface getServiceUsersInterface() {
		return serviceUsersInterface;
	}
	@Autowired
	public void setServiceUsersInterface(ServiceUsersInterface serviceUsersInterface) {
		this.serviceUsersInterface = serviceUsersInterface;
	}
	public ServiceCommentInterface getServiceCommentInterface() {
		return serviceCommentInterface;
	}
	@Autowired
	public void setServiceCommentInterface(ServiceCommentInterface serviceCommentInterface) {
		this.serviceCommentInterface = serviceCommentInterface;
	}
	public ServiceArticlesInterface getServiceArticlesInterface() {
		return serviceArticlesInterface;
	}
	@Autowired
	public void setServiceArticlesInterface(ServiceArticlesInterface serviceArticlesInterface) {
		this.serviceArticlesInterface = serviceArticlesInterface;
	}      
}