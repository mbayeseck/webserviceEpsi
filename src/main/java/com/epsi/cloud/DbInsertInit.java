package com.epsi.cloud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DbInsertInit implements ApplicationListener<ContextRefreshedEvent>{
	
	ServiceUsersInterface serviceUsersInterface;
	ServiceArticlesInterface serviceArticlesInterface;
	ServiceCommentInterface serviceCommentInterface;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent context) {
		 Users user1 = new Users("mbaye", "mbayekebe.seck@epsi.fr", 
				 "mba", "Etudiant EPSI I5 2017-2018", "http://mbaye/image.jpg",
				  new Date(), new Date(),true);
		 Users user2 = new Users("florent", "florent.schmitt@epsi.fr",
				 "flo", "Etudiant EPSI I5", "http://florent/image.jpg",
				  new Date(), new Date(),true); 
		 Users user3 = new Users("gregory", "gregory.kalouguine@epsi.fr",
				 "gre", "Etudiant EPSI I5 - 2018", "http://gregory/image.jpg",
				  new Date(), new Date(),true);
		 Users user4 = new Users("david", "david.devos@epsi.fr",
				 "dav", "Etudiant EPSI I5 - 1718", "http://david/image.jpg",
				  new Date(), new Date(),true); 
		 Users user5 = new Users("francois", "francois.teychene@gmail.com",
				 "fra", "Prof EPSI I5-1718", "http://francois/image.jpg",
				  new Date(), new Date(),true);
		 Articles article1 = new Articles(1, "Workshop EPSI 2017 -2018",
				 "Compétition annuelle pour l'école d'ingénieur EPSI",
				 "1234", "Cet Article 1");
		 Articles article2 = new Articles(2, "Cloud Tour 2017 -2018",
				 "Présentation cloud computing annuelle",
				 "12345", "Cet Article 2");
		 Articles article3 = new Articles(4, "Devops 2017 - 2018 à Montpellier",
				 "Présentation Devops des ESN Montpellier",
				 "123456", "Cet Article 3");
		 Comments comment1 = new Comments(1, "12345", "Un évenement à ne pas rater,"
		 		+ " rendez-vous à Montpellier, Marseille et Grenoble.",
		 		new Date(), new Date());
		 Comments comment2 = new Comments(4, "123456", "Devops : Un évenement à ne pas rater,"
			 		+ " rendez-vous à Montpellier à la place de la Comédie.",
			 		new Date(), new Date());
		 Comments comment3 = new Comments(1, "1234", "Cette année le sujet,"
			 		+ " la banque de demain.",
			 		new Date(), new Date());
		 Comments comment4 = new Comments(5, "123456", "Devops : Présentation de Docker,"
				 	+ " à Montpellier à la place de la Comédie.",
				 	new Date(), new Date());
		 Comments comment5 = new Comments(3, "123456", "Est ce qu'il aura une présentation,"
				 	+ " de SaltStack.",
				 	new Date(), new Date());
		 Comments comment6 = new Comments(5, "123456", "Oui il y aura une présentation de Salt,"
				 	+ " industrialisation des déploiements.",
				 	new Date(), new Date());
		 serviceUsersInterface.save(user1);
		 serviceUsersInterface.save(user2);
		 serviceUsersInterface.save(user3);
		 serviceUsersInterface.save(user4);
		 serviceUsersInterface.save(user5);
		 serviceArticlesInterface.save(article1);
		 serviceArticlesInterface.save(article2);
		 serviceArticlesInterface.save(article3);
		 serviceCommentInterface.save(comment1);
		 serviceCommentInterface.save(comment2);
		 serviceCommentInterface.save(comment3);
		 serviceCommentInterface.save(comment4);
		 serviceCommentInterface.save(comment5);
		 serviceCommentInterface.save(comment6);
	}
	public ServiceUsersInterface getServiceUsersInterface() {
		return serviceUsersInterface;
	}
	@Autowired
	public void setServiceUsersInterface(ServiceUsersInterface serviceUsersInterface) {
		this.serviceUsersInterface = serviceUsersInterface;
	}
	public ServiceArticlesInterface getServiceArticlesInterface() {
		return serviceArticlesInterface;
	}
	@Autowired
	public void setServiceArticlesInterface(ServiceArticlesInterface serviceArticlesInterface) {
		this.serviceArticlesInterface = serviceArticlesInterface;
	}
	public ServiceCommentInterface getServiceCommentInterface() {
		return serviceCommentInterface;
	}
	@Autowired
	public void setServiceCommentInterface(ServiceCommentInterface serviceCommentInterface) {
		this.serviceCommentInterface = serviceCommentInterface;
	}	
}
