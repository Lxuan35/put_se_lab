# Auction system

## Introduction

Specification of functional requirements as part of computerisation of the product sale process based on the auction mechanism.

## Business processes

---
<a id="bc1"></a>
### BC1: Auction sale

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Description:** Business process describing a sale by the auction mechanism.

**Main scenario:**
1. [Seller](#ac1) offers the product at an auction. ([UC1](#uc1))
2. [Buyer](#ac2) offers a bid for the product that is higher than the currently highest bid. ([UC2](#uc2), [BR1](#br1))
3. [Buyer](#ac2) wins the auction ([BR2](#br2))
4. [Buyer](#ac2) transfers the amount due to the Seller. ([UC3](#uc3))
5. [Seller](#ac1) transfers the product to the Buyer. ([UC4](#uc4))

**Alternative scenarios:** 

2.A. Buyer's bid has been outbid and [Buyer](#ac2) wants to outbid the current highest bid.
* 2.A.1. Continue at step 2.

3.A. Auction time has elapsed and [Buyer](#ac2) has lost the auction. ([BR2](#br2))
* 3.A.1. End of use case.

---

## Actors

<a id="ac1"></a>
### AC1: Seller

A person offering goods at an auction.

<a id="ac2"></a>
### AC2: Buyer

A person intending to purchase a product at an auction..


## User level use cases

### Actors and their goals 

[Seller](#ac1):
* [UC1](#uc1): Offering a product at an auction
* [UC4](#uc4): Delivering the product to the buyer
* UC5: Canceling an auction
* UC6: Editing auction details
* UC7: Viewing own auctions
* UC8: Viewing bidding history
* UC9: Rating a buyer
* UC10: Reporting a payment issue

[Buyer](#ac2):
* [UC2](#uc2): Placing a bid in an auction
* [UC3](#uc3): Paying for a won auction
* UC11: Searching/browsing auctions
* UC12: Viewing auction details
* UC13: Viewing bidding history
* UC14: Adding auction to watchlist
* UC15: Withdrawing a bid
* UC16: Rating a seller


---
<a id="uc1"></a>
### UC1: Offering a product at an auction

**Actors:** [Seller](#ac1)

**Main scenario:**
1. [Seller](#ac1) reports to the system the willingness to offer the product up at an auction.
2. System asks for the product data and initial price.
3. [Seller](#ac1) provides product data and the initial price.
4. System verifies data correctness.
5. System informs that the product has been successfully put up for auction.

**Alternative scenarios:** 

4.A. Incorrect or incomplete product data has been entered.
* 4.A.1. informs about incorrectly entered data.
* 4.A.2. Continue at step 2.

---

<a id="uc2"></a>
### UC2: Placing a bid in an auction

**Actors:** [Buyer](#ac2)

**Main scenario:**
1. [Buyer](#ac2) selects an active auction.
2. System displays auction details including current highest bid.
3. [Buyer](#ac2) submits a bid amount.
4. System verifies that the bid satisfies [BR1](#br1).
5. System records the bid.
6. System informs the [Buyer](#ac2) that the bid was accepted.

**Alternative scenarios:**

4.A. Bid is lower than required minimum.
* 4.A.1. System informs the Buyer that the bid is too low.
* 4.A.2. Continue at step 3.

4.B. Auction has already ended.
* 4.B.1. System informs the Buyer that the auction is closed.
* 4.B.2. End of use case.

---

<a id="uc3"></a>
### UC3: Paying for a won auction

**Actors:** [Buyer](#ac2)

**Main scenario:**
1. System informs the [Buyer](#ac2) that they have won the auction ([BR2](#br2)).
2. [Buyer](#ac2) declares in the system the intention to pay.
3. System provides payment details.
4. [Buyer](#ac2) confirms that the payment has been made.
5. System records the payment and marks it as completed.

**Alternative scenarios:**

4.A. Payment is not completed.
* 4.A.1. System reminds the Buyer about the pending payment.
* 4.A.2. Use case continues at step 2.

---

<a id="uc4"></a>
### UC4: Delivering the product to the buyer

**Actors:** [Seller](#ac1)

**Main scenario:**
1. System informs the [Seller](#ac1) that the payment has been completed.
2. [Seller](#ac1) confirms shipment of the product.
3. System records shipment details.
4. System informs the [Buyer](#ac2) that the product has been shipped.

**Alternative scenarios:**

2.A. Seller delays shipment.
* 2.A.1. System sends a reminder to the Seller.
* 2.A.2. Use case continues at step 2.

---


## Business objects (also known as domain or IT objects)

### BO1: Auction

The auction is a form of concluding a sale and purchase transaction in which the Seller specifies the starting bid of the product, while the Buyers may offer their own purchase offer, each time proposing a bid higher than the currently offered bid. The auction ends after a specified period of time. If at least one purchase offer has been submitted, the product is purchased by the Buyer who offered the highest bid. 

Status: Can be Draft, Active, Cancelled, Closed, or Completed.

### BO2: Product

A physical or digital item to be auctioned.

### BO3: Bid

A specific price offer submitted by a [Buyer](#ac2) for a product within a specific auction.

## Business rules

<a id="br1"></a>
### BR1: Bidding at auction

Bidding at auction requires submitting an amount higher than current by a minimum of EUR 1.00

<a id="br2"></a>
### BR2: Winning an auction

Auction is won by [Buyer](#ac2) who submitted the highest bid before the end of the auction (time expires).


## CRUDL Matrix


| Use case                                  | Auction | Product | Bid |
| ----------------------------------------- | ------- | ------- | --- |
| UC1: Offering a product at an auction     |    C    |    C    |  -  |
| UC2: Placing a bid in an auction          |    U    |    R    |  C  |
| UC3: Paying for a won auction             |    U    |    -    |  -  |
| UC4: Delivering the product to the buyer  |    U    |    R    |  -  |
