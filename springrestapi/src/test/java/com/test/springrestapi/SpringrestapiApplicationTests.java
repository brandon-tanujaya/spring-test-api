package com.test.springrestapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringrestapiApplication.class})
class SpringrestapiApplicationTests {

	private WebClient webClient;

	@Test
	void contextLoads() {
		this.exchangeFunction = mock(ExchangeFunction.class);
		ClientResponse mockResponse = mock(ClientResponse.class);
		when(this.exchangeFunction.exchange(this.argumentCaptor.capture())).thenReturn(Mono.just(mockResponse));
		this.webClient = WebClient
				.builder()
				.baseUrl("https://example.com/api")
				.exchangeFunction(exchangeFunction)
				.build();
	}

	private void verifyCalledUrl(String relativeUrl) {
		ClientRequest request = this.argumentCaptor.getValue();
		Assert.assertEquals(String.format("%s%s", BASE_URL, relativeUrl), request.url().toString());
		Mockito.verify(this.exchangeFunction).exchange(request);
		verifyNoMoreInteractions(this.exchangeFunction);
	}

}
