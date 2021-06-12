// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
// import '@testing-library/jest-dom/extend-expect';
import React from 'react';
import { configure } from 'enzyme';
import { mount, shallow, render } from 'enzyme';
import { mountToJson, shallowToJson, renderToJson } from 'enzyme-to-json';
import Adapter from 'enzyme-adapter-react-16';

global.React = React;
global.mount = mount;
global.shallow = shallow;
global.render = render;
global.mountToJson = mountToJson;
global.shallowToJson = shallowToJson;
global.renderToJson = renderToJson;

configure({ adapter: new Adapter() });




// "@testing-library/jest-dom": "^4.2.4",
// "@testing-library/react": "^9.5.0",
// "@testing-library/user-event": "^7.2.1",