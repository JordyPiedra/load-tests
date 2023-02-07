// processor.js
const fs = require('fs');
const formData = require('form-data');

function setJSONBody(requestParams, context, ee, next) {
    // requestParams.body.append('multiparImage', fs.createReadStream(__dirname + '/target/test-classes/img.jpg'));
    const formData = {
        fileOCR: fs.createReadStream('./img.jpg'),
    };

    requestParams.formData = Object.assign({}, requestParams.formData, formData);

    return next();
}


function addException(requestParams, response, context, ee, next) {
    let statusCode = response.statusCode;
    if (statusCode === 201) {
        global.count = global.count ? global.count + 1 : 1;
        if (global.count > 50) {
            console.log("Too many 201 responses:", global.count);
            return next(new Error(`Too many 201 responses: ${global.count}`));

        }
    }
    return next();
}


module.exports = {
    setJSONBody: setJSONBody,
    addException: addException,
}