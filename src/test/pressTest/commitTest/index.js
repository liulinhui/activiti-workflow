const fs = require('fs');
const async = require('async');
const superagent = require('superagent');
let arguments = process.argv.splice(2);
let count = parseInt(arguments[1]);
let random = require('randomstring');
let courseCode = arguments[0];
let log = [];

const workDetail = '张震，1976年10月14日出生于台湾省台北市，祖籍浙江余姚，中国台湾影视男演员、歌手';

let readAccount = function () {
    let account = fs.readFileSync('./account.json');
    return JSON.parse(account.toString());
};

let httpRequest = function (params, cb) {
    superagent
        .get('http://192.168.1.134:8080/api/user/commitWork')
        .query(params)
        .end(function (err, res) {
            if (err) {
                log.push(JSON.stringify(err))
            } else {
                log.push(JSON.stringify(res.body));
                cb(null)
            }
        })
};

let pressTest = function (courseCode, benchmark) {
    let beginTime = new Date().getTime();
    let params = [];
    for (let i = 0; i < count; i++) {
        let param = {
            'courseCode': courseCode,
            'workDetail': workDetail,
            'email': random.generate({length: 20}) + '@qq.com',
            'userName': random.generate({length: 10}),
            'userType': 'student'
        };
        params.push(param);
    }
    async.eachLimit(
        params, benchmark, function (param, callback) {
            httpRequest(param, callback)
        }, function (err) {
            if (err) {
                console.log('*********************request error');
                log.push('*********************request error');
            } else {
                console.log('=====================request success');
                console.log(count + '并发=====================request consume time=' + ((new Date().getTime()) - beginTime)+'ms');
                log.push(count + '并发=====================request consume time=' + ((new Date().getTime()) - beginTime)+'ms');
            }
            fs.writeFileSync('./result/' + courseCode + '_' + count + '.json', JSON.stringify(log))
        }
    )
};

pressTest(courseCode, 300);