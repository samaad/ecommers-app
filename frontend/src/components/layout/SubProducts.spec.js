import SubProducts from './SubProducts';
import { shallowToJson } from 'enzyme-to-json';
import { shallow } from 'enzyme';

describe('SubProducts component', () => {

    it("should render the component", () => {
        const wrapper = shallow(<SubProducts />);
        expect(shallowToJson(wrapper)).toMatchSnapshot();
    });
});

