package com.darkshadowft.smd_assignment3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class Product implements Serializable {

		private String id;
		private String description;
		private String name;
		private String price;
		private float rating;
		private int imgResourceId;
		private transient IProductDAO dao = null;

		public Product(){
				this.id = UUID.randomUUID().toString();
				rating = 0;
				imgResourceId = 0;
		}

		public Product(String description, String name, String price, float rating){
				this.id = UUID.randomUUID().toString();
				this.description = description;
				this.name = name;
				this.price = price;
				this.rating = rating;
		}

		public void setId(String id) {
				this.id = id;
		}

		public String getId(){ return id;}
		public void setDescription(String description){
				this.description = description;
		}

		public String getDescription(){
				return description;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getName() {
				return name;
		}
		public void setPrice(String price){
				this.price = price;
		}

		public String getPrice(){
				return price;
		}
		public void setRating(float rating){
				this.rating = rating;
		}

		public float getRating(){
				return rating;
		}

		public void setImgResourceId(int imgResourceId) {
				this.imgResourceId = imgResourceId;
		}

		public int getImgResourceId() {
				return imgResourceId;
		}

		public IProductDAO getDao() {
				return dao;
		}

		public void setDao(IProductDAO dao) {
				this.dao = dao;
		}

		public void load(Hashtable<String,String> data){
				id = data.get("id");
				description = data.get("description");
				name = data.get("name");
				price = data.get("price");
				rating = Float.parseFloat(data.get("rating"));
				imgResourceId = Integer.parseInt(data.get("imgresourceid"));
		}

		public void save(){
				if (dao != null){
						Hashtable<String,String> data = new Hashtable<String, String>();

						data.put("id",id);
						data.put("name", name);
						data.put("description", description);
						data.put("price", price);
						data.put("rating", Float.toString(rating));
						data.put("imgresourceid", Integer.toString(imgResourceId));

						dao.saveProduct(data);
				}
		}

		public void delete(){
				if (dao != null){
						dao.deleteProduct(id);
				}
		}

		public Hashtable<String, String> getHashTable(){
				Hashtable<String,String> data = new Hashtable<String, String>();

				data.put("id",id);
				data.put("name", name);
				data.put("description", description);
				data.put("price", price);
				data.put("rating", Float.toString(rating));
				data.put("imgResourceId", Integer.toString(imgResourceId));

				return data;
		}

		public static ArrayList<Product> load(IProductDAO dao){
				ArrayList<Product> products = new ArrayList<Product>();
				if(dao != null){

						ArrayList<Hashtable<String,String>> objects = dao.loadProducts();
						for(Hashtable<String,String> obj : objects){
								Product product = new Product();
								product.setDao(dao);
								product.load(obj);
								products.add(product);
						}
				}
				return products;
		}
}
