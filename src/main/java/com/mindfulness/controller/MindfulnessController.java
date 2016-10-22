package com.mindfulness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mindfulness.model.QuoteBean;
import com.mindfulness.service.impl.QuoteGeneratorImpl;

@Controller
public class MindfulnessController {
	@RequestMapping(value = { "*" }, method = { RequestMethod.GET })
	public String QuoteDisplay(ModelMap model) {
		QuoteGeneratorImpl quoteGenerator = new QuoteGeneratorImpl();
		String quote = quoteGenerator.generateQuote();
		model.addAttribute("quote", (Object) quote);
		return "Mindfulness";
	}

	@RequestMapping(value = { "/addquotes" }, method = { RequestMethod.GET })
	public ModelAndView addQuotes() {
		return new ModelAndView("AddQuotes", "command", (Object) new QuoteBean());
	}

	@RequestMapping(value = { "/addquotes" }, method = { RequestMethod.POST })
	public String addAndReturnToHome(@ModelAttribute(value = "QuotesWebApp") QuoteBean quoteBean, ModelMap modelMap) {
		modelMap.addAttribute("quote", (Object) quoteBean.getQuote());
		return "Mindfulness";
	}
}