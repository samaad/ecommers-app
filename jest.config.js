const pattern = [
    'frontend/src/components/**/*.js',
    'frontend/src/main/App.js',
];

module.exports = {
    collectCoverageFrom: pattern,
    collectCoverage: true,
    coverageReporters: ['lcov', 'text'],
    snapshotSerializers: ["enzyme-to-json/serializer"],
    // coverageThreshold: {
    //   global:
    //   {
    //     statements: 95,
    //     branches: 90,
    //     functions: 90,
    //     lines: 95,
    //   },
    // },
    roots: [
        '<rootDir>/frontend/src',
    ],
    setupFiles: ['<rootDir>/frontend/setupTests.js'],
    testEnvironment: "node",
    errorOnDeprecated: true,
    moduleNameMapper: {
        "^.+\\.(css|less|scss)$": "babel-jest"
    },
    moduleFileExtensions: [
        "js",
        "jsx"
    ],
    coverageDirectory: './frontend/coverage/',
    reporters: [
        'default',
        [
            'jest-junit',
            {
                suiteName: 'Content Service Integration Tests',
                outputDirectory: './frontend/reports/test',
                outputName: './frontend/tests-report.xml',
                classNameTemplate: '{classname}-{title}',
                titleTemplate: '{classname}-{title}',
                ancestorSeparator: ' › ',
                usePathForSuiteName: 'true',
            },
        ],
        [
            'jest-html-reporters',
            {
                publicPath: './frontend/reports/test',
                filename: 'test-report.html',
            },
        ],
    ],
}