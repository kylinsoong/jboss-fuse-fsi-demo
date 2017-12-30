/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.poc.fsi.fund;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ceposta (http://christianposta.com/blog)).
 * Modified by Kylin (http://ksoong.org)
 */

@XmlRootElement(name = "transaction")
public class Transaction {
	
    private int fundNumber;
    
    private int balance = 50000;
    
    private boolean denied;
    
    private String deniedCause;

    private String transactionType;

    private String fundName;
    
    private String fundType;
    
    private String fundStatus;

    public int getFundNumber() {
        return fundNumber;
    }

    public void setFundNumber(int fundNumber) {
        this.fundNumber = fundNumber;
    }
  
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isDenied() {
		return denied;
	}

	public void setDenied(boolean denied) {
		this.denied = denied;
	}

	public String getDeniedCause() {
		return deniedCause;
	}

	public void setDeniedCause(String deniedCause) {
		this.deniedCause = deniedCause;
	}

	public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFundStatus() {
		return fundStatus;
	}

	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}
}
