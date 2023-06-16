Replace the text <YOUR_SECRET_API_KEY> in create-payment.php file with your secret API key.

For reference: https://developers.nets.eu/nets-easy/en-EU/docs/web-integration/integrate-nets-easy-checkout-on-your-website-embedded/

For payload.json file, the request body includes:
- Integration type: Either an embedded checkout that uses an iframe on your page or a pre-built checkout page hosted by Nets that the customer will be redirected to.
- Checkout URL: The URL to your checkout page. This URL must match the URL to your checkout page exactly, including protocol (http/https) and the fully qualified domain name (FQDN).
- Terms URL: A URL to a page on your site that describes the payment terms for your webshop.
- Order details: Includes order items, total amount, and currency
Importatnt!
Make sure you replace the line: "url": "https://<YOUR_SERVER>/checkout.html" with the exact URL to the checkout page (created in the next step), or the page will fail to load later on. Verify the protocol (http/https), server address, and port you are using.

For checkout.html file:
- The container element <div id="checkout-container-div"> is the place where the checkout iframe will eventually be embedded.
- Two JavaScripts are loaded: checkout.js from Nets and the custom script.js which you will implement in the next step.
- Since this guide uses the Easy test environment, checkout.js is loaded from test.checkout.dibspayment.eu. When using the live environment, you should instead use checkout.dibspayment.eu.
The checkout.html page should always be requested with a URL parameter called paymentId. This is because the paymentId is needed in order to identify the current payment session when communicating with Nets.

For script.js file:
- Replace <YOUR_CHECKOUT_KEY> with your checkout key for the test environment.
- For the checkout.js script to load correctly, it's important that you have specified the correct URL to your checkout page in Step 3.
- The checkout object will trigger the event 'payment-completed' once the payment has been completed. The callback function we provide navigates to completed.html which you will create in the next step.
