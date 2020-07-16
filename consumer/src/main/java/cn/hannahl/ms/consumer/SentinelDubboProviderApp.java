/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.hannahl.ms.consumer;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Collections;

/**
 * @author fangjian
 */
@SpringBootApplication
public class SentinelDubboProviderApp {

	public static void main(String[] args) {
		SpringApplicationBuilder providerBuilder = new SpringApplicationBuilder();
		providerBuilder.web(WebApplicationType.NONE)
				.sources(com.alibaba.cloud.examples.SentinelDubboProviderApp.class).run(args);

		initRule();
	}

	private static void initRule() {
		FlowRule flowRule = new FlowRule();
		flowRule.setResource(
				"com.alibaba.cloud.examples.FooService:hello(java.lang.String)");
		flowRule.setCount(10);
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		flowRule.setLimitApp("default");
		FlowRuleManager.loadRules(Collections.singletonList(flowRule));
	}

}
