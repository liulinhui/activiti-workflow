let fs = require('fs');
let random = require('randomstring');

let count = 1000;
let obj = {};
for (let i = 0; i < count; i++) {
    obj[random.generate({length: 10})] = random.generate({length: 20}) + 'qq.com';
}
fs.writeFileSync('./account.json',JSON.stringify(obj));
