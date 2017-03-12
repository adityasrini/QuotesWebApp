package com.mindfulness.controller;

import com.mindfulness.model.QuoteBean;
import com.mindfulness.service.QuoteGenerator;
import com.mindfulness.service.impl.QuoteGeneratorImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MindfulnessController {

	/*
	 * For localhost, the address is
	 * http://localhost:8080/QuotesWebApp/mindfulness/ For heroku, it's
	 * http://quotesgenerator.herokuapp.com/mindfulness/
	 */
	
	@RequestMapping(value = {"*"}, method = {RequestMethod.GET})
	public String QuoteDisplay(ModelMap model) {
		QuoteGeneratorImpl quoteGenerator = new QuoteGeneratorImpl();
		String quote = quoteGenerator.generateQuote();
		model.addAttribute("quote", (Object) quote);
		return "Mindfulness";
	}
	
	@RequestMapping(value = {"/addquotes"}, method = {RequestMethod.GET})
	public ModelAndView addQuotes() {
		
		return new ModelAndView("AddQuotes", "command", (Object) new QuoteBean());
	}
	
	@RequestMapping(value = {"/addquotes"}, method = {RequestMethod.POST})
	public String addAndReturnToHome(@ModelAttribute(value = "QuotesWebApp") QuoteBean quoteBean, ModelMap modelMap) {
		modelMap.addAttribute("quote", (Object) quoteBean.getQuote());
		return "Mindfulness";
	}
	
	@RequestMapping(value = {"/listquotes"})
	@ResponseBody
	public static String listQuotes() {
		QuoteGenerator quoteGenerator = new QuoteGeneratorImpl();
		String[] quoteList = quoteGenerator.listAllQuotes();
		String s = "";
		
		for (String s1 : quoteList) {
			s += s1 + "<br>";
		}
		
		
		return s;
	}
}
