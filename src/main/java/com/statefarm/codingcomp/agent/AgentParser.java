package com.statefarm.codingcomp.agent;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.statefarm.codingcomp.bean.Agent;
import com.statefarm.codingcomp.bean.Product;
import com.statefarm.codingcomp.utilities.SFFileReader;

@Component
public class AgentParser {
	@Autowired
	private SFFileReader sfFileReader;

	@Cacheable(value = "agents")
	public Agent parseAgent(String fileName) {
		Agent myAgent=new Agent();
		sfFileReader=new SFFileReader();
		Document doc = Jsoup.parse(sfFileReader.readFile(fileName));
		
		// parse Name
		Element name=doc.select("span[itemprop=\"name\"]").get(0);
		myAgent.setName(name.text());
		
		//parse products
		Elements productList=doc.select("div[itemprop=description]").get(0).select("ul").get(0).select("li");
		Set <Product> setP=new HashSet<>();
		setP.clear();
		for (int i=0;i<productList.size();i++){
			setP.add(Product.fromValue(productList.get(i).text()));
			System.out.println(productList.get(i).text());
		};
		myAgent.setProducts(setP);

		// parse offices:
		
		
		return myAgent;
	}
}
