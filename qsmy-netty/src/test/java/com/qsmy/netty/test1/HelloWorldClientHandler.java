package com.qsmy.netty.test1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author qsmy
 */
public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {

    private byte[] req;
    private int counter;

    public HelloWorldClientHandler() {
        req = ("Unless required by applicable law or agreed to in writing, software\t" +
                "  distributed under the License is distributed on an \"AS IS\" BASIS,\t" +
                "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\t" +
                "  See the License for the specific language governing permissions and\t" +
                "  limitations under the License.This connector uses the BIO implementation that requires the JSSE\t" +
                "  style configuration. When using the APR/native implementation, the\t" +
                "  penSSL style configuration is required as described in the APR/native\t" +
                "  documentation.An Engine represents the entry point (within Catalina) that processes\t" +
                "  every request.  The Engine implementation for Tomcat stand alone\t" +
                "  analyzes the HTTP headers included with the request, and passes them\t" +
                "  on to the appropriate Host (virtual host)# Unless required by applicable law or agreed to in writing, software\t" +
                "# distributed under the License is distributed on an \"AS IS\" BASIS,\t" +
                "# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\t" +
                "# See the License for the specific language governing permissions and\t" +
                "# limitations under the License.# For example, set the org.apache.catalina.util.LifecycleBase logger to log\t" +
                "# each component that extends LifecycleBase changing state:\t" +
                "#org.apache.catalina.util.LifecycleBase.level = FINE\t"
        ).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");

        //将上面的所有字符串作为一个消息体发送出去
        ByteBuf message = Unpooled.buffer(req.length);
        message.writeBytes(req);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String buf = (String) msg;
        System.out.println("Now is : " + buf + " ; the counter is : " + (++counter));
    }
}
