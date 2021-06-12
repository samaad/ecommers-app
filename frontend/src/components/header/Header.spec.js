import Header from './Header';
import { shallowToJson } from 'enzyme-to-json';
import { shallow } from 'enzyme';

describe('Header component', () => {

    it("should render the component", () => {
        const wrapper = shallow(<Header />);
        expect(shallowToJson(wrapper)).toMatchSnapshot();
    });
});

