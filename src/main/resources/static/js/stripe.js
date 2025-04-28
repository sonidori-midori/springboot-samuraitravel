const stripe = Stripe('pk_test_51RGFVJRsQUIpvs87YjqFe5vQVKT8sSSYSbfiD2iYzEHajKeyEDCQdkFYF6xVvwqzsSv54ksVrsBPXFluJaBXKtIV00skg1lG2K');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
 stripe.redirectToCheckout({
   sessionId: sessionId
 })
});