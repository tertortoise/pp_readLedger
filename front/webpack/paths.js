const path = require('path');

const ROOT_DIR = path.resolve(__dirname, '../');

const resolvePath = (...args) => path.resolve(ROOT_DIR, ...args);

const BUILD_DIR = resolvePath('dist');

module.exports = {
    ROOT_DIR,
    BUILD_DIR,
    resolvePath
}
