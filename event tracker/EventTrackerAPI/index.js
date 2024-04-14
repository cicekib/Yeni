const axios = require('axios');
const cheerio = require('cheerio');
const express = require('express');
const bodyParser = require('body-parser'); // Import body-parser
const cors = require('cors');

const app = express();

// Middleware to parse JSON bodies
app.use(express.json());
app.use(bodyParser.json());

const port = 3000;

// Use CORS middleware
app.use(cors());

// Endpoint to fetch event data
app.post('/events', async (req, res) => {
    const city = req.body.city;
    try {
        const response = await axios.get(`https://allevents.in/${city}/business?ref=cityhome-category-box-new`);
        const $ = cheerio.load(response.data);

        const eventList = [];
        $('li.event-card').each((index, element) => {
            const name = $(element).find('.title h3').text().trim();
            const date = $(element).find('.meta-bottom .date').text().trim();
            const link = $(element).data('link');
            const location = $(element).data('location');
            const price = $(element).data('price');
            const imageUrl = $(element).data('imageUrl');

            eventList.push({ name, date, link, location, price, imageUrl });
        });

        console.log("eventListasd", eventList);

        res.json(eventList);
    } catch (error) {
        console.error('Failed to fetch event list:', error);
        res.status(500).send('Failed to fetch event list');
    }
})
//async function fetchEventList() {

/* 
// Fetch and print the event list
fetchEventList().then(events => {
    if (events) {
        events.forEach(event => {
            console.log(`Title: ${event?.name}`);
            console.log(`Date: ${event?.date}`);
            console.log(`Location: ${event?.location}`);
            console.log();
        });
    }
}); */

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
