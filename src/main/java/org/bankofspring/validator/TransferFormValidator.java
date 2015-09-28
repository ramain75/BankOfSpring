package org.bankofspring.validator;

import org.apache.commons.lang3.StringUtils;
import org.bankofspring.web.TransferForm;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

@Component
public class TransferFormValidator {

	public void validateFromAccount(TransferForm transferForm, ValidationContext context) {
		MessageContext messages = context.getMessageContext();

		if (StringUtils.isBlank(transferForm.getFromAccount())) {
			messages.addMessage(new MessageBuilder().error().source("fromAccount")
					.defaultText("From account must have a value").build());
		}
		// TODO validate it's a valid account
	}
	
	public void validateSelectAccount(TransferForm transferForm, ValidationContext context) {
		MessageContext messages = context.getMessageContext();

		if (StringUtils.isBlank(transferForm.getToAccount())) {
			messages.addMessage(new MessageBuilder().error().source("toAccount")
					.defaultText("To account must have a value").build());
		}
		// TODO validate it's a valid account
	}
	
	public void validateSelectAmount(TransferForm transferForm, ValidationContext context) {
		MessageContext messages = context.getMessageContext();

		Long amount = null;
		try {
			amount = Long.parseLong(transferForm.getAmount());
		} catch (NumberFormatException ex) {
			messages.addMessage(new MessageBuilder().error().source("amount")
					.defaultText("The amount must be numeric").build());
			return;
		}
		
		if (amount <= 0) {
			messages.addMessage(new MessageBuilder().error().source("amount")
					.defaultText("The amount must be greater than zero").build());
		}
		// TODO validate it's a valid account
	}
}
